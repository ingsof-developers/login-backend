package com.developers.developers.Repository;

import com.developers.developers.model.entity.Departamentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentosRepository extends JpaRepository<Departamentos, Long> {
    Optional<Departamentos> findById(Long id);
}
