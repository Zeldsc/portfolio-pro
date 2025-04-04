package github.zeldsc.portfoliopro.database.entity.mapper;

import github.zeldsc.portfoliopro.database.entity.PessoaEntity;
import github.zeldsc.portfoliopro.entity.Pessoa;

public class PessoaMapper {

    public static Pessoa toDomain(PessoaEntity entity) {
        return Pessoa.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome())
                .dataNascimento(entity.getDataNascimento())
                .funcionario(entity.isFuncionario())
                .gerente(entity.isGerente())
                .projetos(entity.getProjetos().stream()
                        .map(ProjetoMapper::toDomain)
                        .toList())
                .build();
    }

    public static Pessoa toDomainSemProjetos(PessoaEntity entity) {
        if (entity == null) return null;
        return Pessoa.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .dataNascimento(entity.getDataNascimento())
                .funcionario(entity.isFuncionario())
                .gerente(entity.isGerente())
                .build(); // não mapeia os projetos
    }

    public static PessoaEntity toEntity(Pessoa pessoa) {
        PessoaEntity entity = new PessoaEntity();
        entity.setId(pessoa.getId());
        entity.setNome(pessoa.getNome());
        entity.setDataNascimento(pessoa.getDataNascimento());
        entity.setCpf(pessoa.getCpf());
        entity.setFuncionario(pessoa.isFuncionario());
        entity.setGerente(pessoa.isGerente());
        return entity;
    }
}