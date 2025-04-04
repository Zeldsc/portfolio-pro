package github.zeldsc.portfoliopro.port;

import github.zeldsc.portfoliopro.entity.Projeto;

import java.util.List;

public record ProjetoPaginado(List<Projeto> projetos, int pagina, int tamanho, int total) {}