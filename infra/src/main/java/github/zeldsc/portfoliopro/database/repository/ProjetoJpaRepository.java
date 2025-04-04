package github.zeldsc.portfoliopro.database.repository;


import github.zeldsc.portfoliopro.database.entity.ProjetoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoJpaRepository extends JpaRepository<ProjetoEntity, Long> {
    List<ProjetoEntity> findAllBy(Pageable pageable);
}