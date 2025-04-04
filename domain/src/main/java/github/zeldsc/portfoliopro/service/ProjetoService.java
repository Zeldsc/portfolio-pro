package github.zeldsc.portfoliopro.service;

import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.entity.Projeto;
import github.zeldsc.portfoliopro.port.ProjetoPaginado;

public interface ProjetoService {
    Projeto cadastrar(Projeto projeto);
    Projeto atualizar(Long id, Projeto projetoAtualizado);
    void excluir(Long id);
    Projeto buscarPorId(Long id);
    public ProjetoPaginado listarPaginado(Paginacao paginacao) ;
}