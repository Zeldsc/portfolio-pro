package github.zeldsc.portfoliopro.database.repository;

import github.zeldsc.portfoliopro.database.entity.ProjetoEntity;
import github.zeldsc.portfoliopro.database.entity.mapper.ProjetoMapper;
import github.zeldsc.portfoliopro.util.PaginacaoPageableConverter;
import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.entity.Projeto;
import github.zeldsc.portfoliopro.port.ProjetoRepositoryPort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjetoRepositoryAdapter implements ProjetoRepositoryPort {

    private final ProjetoJpaRepository projetoJpaRepository;

    public ProjetoRepositoryAdapter(ProjetoJpaRepository springRepo) {
        this.projetoJpaRepository = springRepo;
    }

    @Override
    public Projeto salvar(Projeto projeto) {
        ProjetoEntity entity = ProjetoMapper.toEntity(projeto);
        return ProjetoMapper.toDomain(projetoJpaRepository.save(entity));
    }

    @Override
    public Optional<Projeto> buscarPorId(Long id) {
        return projetoJpaRepository.findById(id).map(ProjetoMapper::toDomain);
    }

    @Override
    public List<Projeto> buscarPaginado(Paginacao paginacao) {
        Pageable pageable = PaginacaoPageableConverter.toPageable(paginacao);
        Page<ProjetoEntity> projetos = projetoJpaRepository.findAll(pageable);
        return projetos.stream()
                .map(ProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public long contarTotal() {
        return projetoJpaRepository.count();
    }

    @Override
    public void remover(Projeto projeto) {
        projetoJpaRepository.deleteById(projeto.getId());
    }
}
