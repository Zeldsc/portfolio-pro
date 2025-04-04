package github.zeldsc.portfoliopro.database.entity.mapper;


import github.zeldsc.portfoliopro.database.entity.PessoaEntity;
import github.zeldsc.portfoliopro.database.entity.ProjetoEntity;
import github.zeldsc.portfoliopro.entity.Projeto;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjetoMapper {

    public static Projeto toDomain(ProjetoEntity entity) {
        if (entity == null) return null;
        return Projeto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .dataInicio(entity.getDataInicio())
                .dataPrevisaoFim(entity.getDataPrevisaoFim())
                .dataFim(entity.getDataFim())
                .descricao(entity.getDescricao())
                .status(entity.getStatus())
                .orcamento(entity.getOrcamento())
                .risco(entity.getRisco())
                .gerente(PessoaMapper.toDomainSemProjetos(entity.getGerente()))
                .membros(entity.getMembros().stream()
                        .map(PessoaMapper::toDomainSemProjetos)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ProjetoEntity toEntity(Projeto projeto) {
        if (projeto == null) return null;
        ProjetoEntity entity = new ProjetoEntity();
        entity.setId(projeto.getId());
        entity.setNome(projeto.getNome());
        entity.setDataInicio(projeto.getDataInicio());
        entity.setDataPrevisaoFim(projeto.getDataPrevisaoFim());
        entity.setDataFim(projeto.getDataFim());
        entity.setDescricao(projeto.getDescricao());
        entity.setStatus(projeto.getStatus());
        entity.setOrcamento(projeto.getOrcamento());
        entity.setRisco(projeto.getRisco());
        entity.setGerente(PessoaMapper.toEntity(projeto.getGerente()));

        Set<PessoaEntity> membros = projeto.getMembros().stream()
                .map(PessoaMapper::toEntity)
                .collect(Collectors.toSet());
        entity.setMembros(membros);

        return entity;
    }
} 