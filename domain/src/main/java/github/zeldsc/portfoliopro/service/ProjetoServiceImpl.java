package github.zeldsc.portfoliopro.service;

import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Projeto;
import github.zeldsc.portfoliopro.enums.Status;
import github.zeldsc.portfoliopro.exception.ProjetoNaoEncontradoException;
import github.zeldsc.portfoliopro.exception.ValidacaoNegocioException;
import github.zeldsc.portfoliopro.port.ProjetoPaginado;
import github.zeldsc.portfoliopro.port.ProjetoRepositoryPort;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public final class ProjetoServiceImpl implements ProjetoService {

    private static final Logger logger = Logger.getLogger(ProjetoServiceImpl.class.getName());
    private final ProjetoRepositoryPort projetoRepository;

    public ProjetoServiceImpl(ProjetoRepositoryPort projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public Projeto cadastrar(Projeto projeto) {
        logger.info("Cadastrando novo porojeto: " + projeto.getNome());
        validar(projeto);
        return projetoRepository.salvar(projeto);
    }

    @Override
    public Projeto atualizar(Long id, Projeto projetoAtualizado) {
        var existente = projetoRepository.buscarPorId(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));

        validar(projetoAtualizado);

        return projetoRepository.salvar(
            Projeto.builder()
                .id(id)
                .nome(projetoAtualizado.getNome())
                .dataInicio(projetoAtualizado.getDataInicio())
                .dataPrevisaoFim(projetoAtualizado.getDataPrevisaoFim())
                .dataFim(projetoAtualizado.getDataFim())
                .descricao(projetoAtualizado.getDescricao())
                .status(projetoAtualizado.getStatus())
                .orcamento(projetoAtualizado.getOrcamento())
                .risco(projetoAtualizado.getRisco())
                .gerente(projetoAtualizado.getGerente())
                .membros(projetoAtualizado.getMembros())
                .build()
        );
    }

    @Override
    public void excluir(Long id) {
        var projeto = projetoRepository.buscarPorId(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
        validarExclusao(projeto);
        projetoRepository.remover(projeto);
    }

    @Override
    public Projeto buscarPorId(Long id) {
        return projetoRepository.buscarPorId(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    @Override
    public ProjetoPaginado listarPaginado(Paginacao paginacao) {
        if (paginacao.pagina() < 0 || paginacao.tamanho() <= 0)
            throw new ValidacaoNegocioException("Parâmetros de paginação inválidos.");

        long total = projetoRepository.contarTotal();
        if (paginacao.offset() >= total)
            return new ProjetoPaginado(List.of(), paginacao.pagina(), paginacao.tamanho(), (int) total);

        List<Projeto> projetos = projetoRepository.buscarPaginado(paginacao);
        return new ProjetoPaginado(projetos, paginacao.pagina(), paginacao.tamanho(), (int) total);
    }

    private void validar(Projeto p) {
        Optional.ofNullable(p.getNome())
                .filter(s -> !s.isBlank())
                .orElseThrow(() -> new ValidacaoNegocioException("Nome do projeto é obrigatório."));

        Optional.ofNullable(p.getDataInicio())
                .orElseThrow(() -> new ValidacaoNegocioException("Data de início é obrigatória."));

        if (p.getDataPrevisaoFim() != null && p.getDataPrevisaoFim().isBefore(p.getDataInicio()))
            throw new ValidacaoNegocioException("A data prevista de término não pode ser anterior à data de início.");

        if (p.getDataFim() != null && p.getDataFim().isBefore(p.getDataInicio()))
            throw new ValidacaoNegocioException("A data real de término não pode ser anterior à data de início.");

        Optional.ofNullable(p.getStatus())
                .orElseThrow(() -> new ValidacaoNegocioException("Status do projeto é obrigatório."));

        Optional.ofNullable(p.getGerente())
                .filter(Pessoa::isGerente)
                .orElseThrow(() -> new ValidacaoNegocioException("O gerente responsável deve ser válido e marcado como gerente."));

        if (p.getOrcamento()== null || p.getOrcamento().compareTo(BigDecimal.ZERO) < 0)
            throw new ValidacaoNegocioException("Orçamento deve ser preenchido e não pode ser negativo.");

        if (p.getMembros() != null && p.getMembros().stream().anyMatch(m -> !m.isFuncionario()))
            throw new ValidacaoNegocioException("Todos os membros associados ao projeto devem ser funcionários.");
    }

    private void validarExclusao(Projeto projeto) {
        if (EnumSet.of(Status.INICIADO, Status.EM_ANDAMENTO, Status.ENCERRADO).contains(projeto.getStatus())) {
            throw new ValidacaoNegocioException(
                    "Projetos com status '%s' não podem ser excluídos.".formatted(projeto.getStatus().getDescricao()));
        }
    }
}
