package github.zeldsc.portfoliopro.port;

import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Paginacao;

import java.util.List;
import java.util.Optional;

public interface PessoaRepositoryPort {
    Pessoa salvar(Pessoa pessoa);
    Optional<Pessoa> buscarPorCpf(String cpf);
    void remover(Pessoa pessoa);
    List<Pessoa> buscarPaginado(Paginacao paginacao);
    List<Pessoa> buscarTodos();
    long contarTotal();
}
