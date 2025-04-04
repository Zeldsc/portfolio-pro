package github.zeldsc.portfoliopro.database.repository;

import github.zeldsc.portfoliopro.database.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long> {
    Optional<PessoaEntity> findByCpf(String cpf);
}