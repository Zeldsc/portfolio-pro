package github.zeldsc.portfoliopro.service;

import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Projeto;
import github.zeldsc.portfoliopro.enums.Risco;
import github.zeldsc.portfoliopro.enums.Status;
import github.zeldsc.portfoliopro.exception.ProjetoNaoEncontradoException;
import github.zeldsc.portfoliopro.exception.ValidacaoNegocioException;
import github.zeldsc.portfoliopro.port.ProjetoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjetoServiceImplTest {

    private ProjetoRepositoryPort repository;
    private ProjetoServiceImpl service;

    @BeforeEach
    void setup() {
        repository = mock(ProjetoRepositoryPort.class);
        service = new ProjetoServiceImpl(repository);
    }

    @Test
    void deveCadastrarProjetoComSucesso() {
        Projeto projeto = criarProjeto();
        when(repository.salvar(projeto)).thenReturn(projeto);

        Projeto salvo = service.cadastrar(projeto);

        assertThat(salvo).isEqualTo(projeto);
        verify(repository).salvar(projeto);
    }

    @Test
    void deveLancarErroQuandoDataInicioForNula() {
        Projeto projeto = projetoComNome(criarProjeto(), "Projeto sem data");
        Projeto finalProjeto = Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(null)
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .gerente(projeto.getGerente())
                .membros(projeto.getMembros())
                .risco(projeto.getRisco())
                .build();

        assertThatThrownBy(() -> service.cadastrar(finalProjeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("Data de início é obrigatória");
    }

    @Test
    void deveLancarErroQuandoDataPrevisaoFimAntesDaDataInicio() {
        Projeto projeto = criarProjeto();
        Projeto finalProjeto = Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(LocalDate.now())
                .dataPrevisaoFim(LocalDate.now().minusDays(1))
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .gerente(projeto.getGerente())
                .membros(projeto.getMembros())
                .risco(projeto.getRisco())
                .build();

        assertThatThrownBy(() -> service.cadastrar(finalProjeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("A data prevista de término não pode ser anterior à data de início");
    }

    @Test
    void deveLancarErroQuandoDataFimAntesDaDataInicio() {
        Projeto projeto = criarProjeto();
        Projeto finalProjeto = Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(LocalDate.now())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(LocalDate.now().minusDays(2))
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .gerente(projeto.getGerente())
                .membros(projeto.getMembros())
                .risco(projeto.getRisco())
                .build();

        assertThatThrownBy(() -> service.cadastrar(finalProjeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("A data real de término não pode ser anterior à data de início");
    }

    @Test
    void deveLancarErroQuandoStatusForNulo() {
        Projeto projeto = criarProjeto();
        Projeto finalProjeto = projetoComStatus(projeto, null);

        assertThatThrownBy(() -> service.cadastrar(finalProjeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("Status do projeto é obrigatório");
    }

    @Test
    void deveLancarErroQuandoGerenteNaoForValido() {
        Pessoa gerenteInvalido = Pessoa.builder().id(99L).nome("Invalido").cpf("123").funcionario(true).gerente(false).build();
        Projeto projeto = criarProjeto();
        Projeto finalProjeto = Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .gerente(gerenteInvalido)
                .membros(projeto.getMembros())
                .risco(projeto.getRisco())
                .build();

        assertThatThrownBy(() -> service.cadastrar(finalProjeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("O gerente responsável deve ser válido e marcado como gerente");
    }

    @Test
    void deveLancarErroQuandoOrcamentoNegativo() {
        Projeto projeto = criarProjeto();
        Projeto finalProjeto = Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(new BigDecimal(-100))
                .gerente(projeto.getGerente())
                .membros(projeto.getMembros())
                .risco(projeto.getRisco())
                .build();

        assertThatThrownBy(() -> service.cadastrar(finalProjeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("Orçamento deve ser preenchido e não pode ser negativo.");
    }

    @Test
    void deveAtualizarProjetoComSucesso() {
        Projeto original = criarProjeto();
        Projeto atualizado = Projeto.builder()
                .id(original.getId())
                .nome("Atualizado")
                .dataInicio(original.getDataInicio())
                .dataPrevisaoFim(original.getDataPrevisaoFim())
                .dataFim(original.getDataFim())
                .descricao(original.getDescricao())
                .status(original.getStatus())
                .orcamento(original.getOrcamento())
                .gerente(original.getGerente())
                .membros(original.getMembros())
                .risco(original.getRisco())
                .build();

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(original));
        when(repository.salvar(any())).thenReturn(atualizado);

        Projeto resultado = service.atualizar(1L, atualizado);

        assertThat(resultado.getNome()).isEqualTo("Atualizado");
    }

    @Test
    void deveExcluirProjetoComStatusPermitido() {
        Projeto projeto = criarProjeto();
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(projeto));

        service.excluir(1L);

        verify(repository).remover(projeto);
    }

    @Test
    void naoDeveExcluirProjetoComStatusIniciadoOuAndamentoOuEncerrado() {
        for (Status status : Set.of(Status.INICIADO, Status.EM_ANDAMENTO, Status.ENCERRADO)) {
            Projeto projeto = criarProjetoComStatus(status);
            when(repository.buscarPorId(projeto.getId())).thenReturn(Optional.of(projeto));

            assertThatThrownBy(() -> service.excluir(projeto.getId()))
                    .isInstanceOf(ValidacaoNegocioException.class)
                    .hasMessageContaining("não podem ser excluídos");
        }
    }

    @Test
    void deveRetornarProjetoPorId() {
        Projeto projeto = criarProjeto();
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(projeto));

        Projeto encontrado = service.buscarPorId(1L);
        assertThat(encontrado).isEqualTo(projeto);
    }

    @Test
    void deveLancarErroQuandoProjetoNaoEncontrado() {
        when(repository.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(1L))
                .isInstanceOf(ProjetoNaoEncontradoException.class)
                .hasMessageContaining("Projeto com ID 1 não encontrado");
    }

    @Test
    void deveLancarErroQuandoMembroNaoForFuncionario() {
        Pessoa invalido = Pessoa.builder().id(11L).nome("Não funcionário").funcionario(false).cpf("123").build();
        Projeto projeto = criarProjetoComMembros(List.of(invalido));

        assertThatThrownBy(() -> service.cadastrar(projeto))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("devem ser funcionários");
    }

    @Test
    void deveAceitarTodosStatusValidosUnicos() {
        for (Status status : Status.values()) {
            Projeto projeto = criarProjetoComStatus(status);
            when(repository.salvar(projeto)).thenReturn(projeto);
            Projeto salvo = service.cadastrar(projeto);
            assertThat(salvo).isEqualTo(projeto);
        }
    }

    @Test
    void deveAceitarClassificacaoDeRiscoBaixoMedioAlto() {
        for (Risco risco : Risco.values()) {
            Projeto projeto = criarProjetoComRisco(risco);
            when(repository.salvar(projeto)).thenReturn(projeto);
            Projeto salvo = service.cadastrar(projeto);
            assertThat(salvo.getRisco()).isEqualTo(risco);
        }
    }

    private Projeto criarProjeto() {
        Pessoa gerente = Pessoa.builder().id(10L).nome("Gerente").gerente(true).funcionario(true).cpf("000.000.000-00").build();
        return Projeto.builder()
                .id(1L)
                .nome("Sistema X")
                .dataInicio(LocalDate.now())
                .dataPrevisaoFim(LocalDate.now().plusDays(30))
                .descricao("Projeto exemplo")
                .status(Status.EM_ANALISE)
                .orcamento(new BigDecimal(50000))
                .gerente(gerente)
                .membros(List.of(gerente))
                .risco(Risco.BAIXO)
                .build();
    }

    private Projeto criarProjetoComStatus(Status status) {
        Projeto projeto = criarProjeto();
        return Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(status)
                .orcamento(projeto.getOrcamento())
                .gerente(projeto.getGerente())
                .membros(projeto.getMembros())
                .risco(projeto.getRisco())
                .build();
    }

    private Projeto criarProjetoComRisco(Risco risco) {
        Projeto projeto = criarProjeto();
        return Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .gerente(projeto.getGerente())
                .membros(projeto.getMembros())
                .risco(risco)
                .build();
    }

    private Projeto criarProjetoComMembros(List<Pessoa> membros) {
        Projeto projeto = criarProjeto();
        return Projeto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .dataFim(projeto.getDataFim())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .gerente(projeto.getGerente())
                .membros(membros)
                .risco(projeto.getRisco())
                .build();
    }


    private Projeto projetoComStatus(Projeto base, Status status) {
        return Projeto.builder()
                .id(base.getId())
                .nome(base.getNome())
                .dataInicio(base.getDataInicio())
                .dataPrevisaoFim(base.getDataPrevisaoFim())
                .dataFim(base.getDataFim())
                .descricao(base.getDescricao())
                .status(status)
                .orcamento(base.getOrcamento())
                .gerente(base.getGerente())
                .membros(base.getMembros())
                .risco(base.getRisco())
                .build();
    }

    private Projeto projetoComNome(Projeto base, String nome) {
        return Projeto.builder()
                .id(base.getId())
                .nome(nome)
                .dataInicio(base.getDataInicio())
                .dataPrevisaoFim(base.getDataPrevisaoFim())
                .dataFim(base.getDataFim())
                .descricao(base.getDescricao())
                .status(base.getStatus())
                .orcamento(base.getOrcamento())
                .gerente(base.getGerente())
                .membros(base.getMembros())
                .risco(base.getRisco())
                .build();
    }
}
