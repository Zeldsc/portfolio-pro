package github.zeldsc.portfoliopro.service;

import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Paginacao;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    Pessoa cadastrar(Pessoa pessoa);
    Pessoa atualizar(String cpf, Pessoa pessoaAtualizada);
    void excluir(String cpf);
    Optional<Pessoa> buscarPorCpf(String cpf);
    List<Pessoa> listar(Paginacao paginacao);
    List<Pessoa> listar();
}
