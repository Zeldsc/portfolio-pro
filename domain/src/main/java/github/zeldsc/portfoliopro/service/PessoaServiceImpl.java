package github.zeldsc.portfoliopro.service;

import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.exception.ValidacaoNegocioException;
import github.zeldsc.portfoliopro.port.PessoaRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PessoaServiceImpl implements PessoaService {

    private static final Logger logger = Logger.getLogger(PessoaServiceImpl.class.getName());
    private final PessoaRepositoryPort pessoaRepository;

    public PessoaServiceImpl(PessoaRepositoryPort pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {
        logger.info("Cadastrando nova pessoa com CPF: " + pessoa.getCpf());
        validar(pessoa);
        var salva = pessoaRepository.salvar(pessoa);
        logger.info("Pessoa cadastrada com sucesso: " + salva.getCpf());
        return salva;
    }

    @Override
    public Pessoa atualizar(String cpf, Pessoa pessoaAtualizada) {
        logger.info("Atualizando pessoa com CPF: " + cpf);
        var existente = pessoaRepository.buscarPorCpf(cpf)
                .orElseThrow(() -> new ValidacaoNegocioException("Pessoa com CPF %s não encontrada.".formatted(cpf)));

        var atualizada = Pessoa.builder()
                .id(existente.getId())
                .nome(pessoaAtualizada.getNome())
                .cpf(cpf)
                .dataNascimento(pessoaAtualizada.getDataNascimento())
                .funcionario(pessoaAtualizada.isFuncionario())
                .gerente(pessoaAtualizada.isGerente())
                .build();

        var salva = pessoaRepository.salvar(atualizada);
        logger.info("Pessoa atualizada com sucesso: " + cpf);
        return salva;
    }

    @Override
    public void excluir(String cpf) {
        logger.info("Excluindo pessoa com CPF: " + cpf);
        var pessoa = pessoaRepository.buscarPorCpf(cpf)
                .orElseThrow(() -> new ValidacaoNegocioException("Pessoa com CPF %s não encontrada.".formatted(cpf)));
        pessoaRepository.remover(pessoa);
        logger.info("Pessoa removida com sucesso: " + cpf);
    }

    @Override
    public Optional<Pessoa> buscarPorCpf(String cpf) {
        logger.fine("Buscando pessoa com CPF: " + cpf);
        return pessoaRepository.buscarPorCpf(cpf);
    }

    @Override
    public List<Pessoa> listar(Paginacao paginacao) {
        logger.fine("Listando pessoas: página %d, tamanho %d".formatted(paginacao.pagina(), paginacao.tamanho()));
        return pessoaRepository.buscarPaginado(paginacao);
    }

    @Override
    public List<Pessoa> listar() {
        return pessoaRepository.buscarTodos();
    }

    private void validar(Pessoa p) {
        logger.fine("Validando pessoa com CPF: " + p.getCpf());
        Optional.ofNullable(p.getNome())
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new ValidacaoNegocioException("Nome da pessoa é obrigatório."));

        Optional.ofNullable(p.getCpf())
                .filter(c -> !c.isBlank())
                .orElseThrow(() -> new ValidacaoNegocioException("CPF é obrigatório."));

        if (p.getCpf().length() != 14)
            throw new ValidacaoNegocioException("CPF deve conter 14 caracteres (com pontuação).");
    }
}