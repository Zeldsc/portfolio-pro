package github.zeldsc.portfoliopro.database.repository;

import github.zeldsc.portfoliopro.database.entity.PessoaEntity;
import github.zeldsc.portfoliopro.database.entity.mapper.PessoaMapper;
import github.zeldsc.portfoliopro.util.PaginacaoPageableConverter;
import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.port.PessoaRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryAdapter implements PessoaRepositoryPort {

    private final PessoaJpaRepository repository;

    public PessoaRepositoryAdapter(PessoaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        PessoaEntity entity = PessoaMapper.toEntity(pessoa);
        return PessoaMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Pessoa> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).map(PessoaMapper::toDomain);
    }

    @Override
    public void remover(Pessoa pessoa) {
        repository.deleteById(pessoa.getId());
    }

    @Override
    public List<Pessoa> buscarPaginado(Paginacao paginacao) {
        return repository.findAll(PaginacaoPageableConverter.toPageable(paginacao))
            .stream()
            .map(PessoaMapper::toDomain)
            .toList();
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return repository.findAll()
            .stream()
            .map(PessoaMapper::toDomain)
            .toList();
    }

    @Override
    public long contarTotal() {
        return repository.count();
    }
} 
