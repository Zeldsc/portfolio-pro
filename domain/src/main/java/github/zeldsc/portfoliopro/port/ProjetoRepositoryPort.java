package github.zeldsc.portfoliopro.port;

import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.entity.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepositoryPort {
    Projeto salvar(Projeto projeto);
    Optional<Projeto> buscarPorId(Long id);
    List<Projeto> buscarPaginado(Paginacao paginacao);
    long contarTotal();
    void remover(Projeto projeto);
}