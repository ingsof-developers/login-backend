package com.developers.developers.Repository;

import com.developers.developers.model.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Students, Long> {
    Optional<Students> findById(Long id);

}
