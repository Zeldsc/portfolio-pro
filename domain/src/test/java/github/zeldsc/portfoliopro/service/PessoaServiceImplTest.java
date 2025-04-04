package github.zeldsc.portfoliopro.service;

import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.exception.ValidacaoNegocioException;
import github.zeldsc.portfoliopro.port.PessoaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaServiceImplTest {

    private PessoaRepositoryPort repository;
    private PessoaServiceImpl service;

    @BeforeEach
    void setup() {
        repository = mock(PessoaRepositoryPort.class);
        service = new PessoaServiceImpl(repository);
    }

    @Test
    void deveCadastrarPessoaComSucesso() {
        Pessoa pessoa = criarPessoa();
        when(repository.salvar(pessoa)).thenReturn(pessoa);

        Pessoa salva = service.cadastrar(pessoa);

        assertThat(salva).isEqualTo(pessoa);
        verify(repository).salvar(pessoa);
    }

    @Test
    void naoDeveCadastrarPessoaSemCpf() {
        Pessoa pessoa = criarPessoa();
        Pessoa finalPessoa = Pessoa.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .funcionario(pessoa.isFuncionario())
                .gerente(pessoa.isGerente())
                .cpf(null)
                .build();

        assertThatThrownBy(() -> service.cadastrar(finalPessoa))
                .isInstanceOf(ValidacaoNegocioException.class)
                .hasMessageContaining("CPF");
    }

    @Test
    void deveBuscarPessoaPorCpf() {
        Pessoa pessoa = criarPessoa();
        when(repository.buscarPorCpf("111.111.111-11")).thenReturn(Optional.of(pessoa));

        Optional<Pessoa> encontrada = service.buscarPorCpf("111.111.111-11");

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getCpf()).isEqualTo("111.111.111-11");
    }

    private Pessoa criarPessoa() {
        return Pessoa.builder()
                .id(1L)
                .nome("Fulano")
                .cpf("111.111.111-11")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .funcionario(true)
                .gerente(false)
                .build();
    }
} 