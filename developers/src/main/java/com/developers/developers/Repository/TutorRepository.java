package com.developers.developers.Repository;

import com.developers.developers.model.entity.Tutores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutores, Long> {
    Optional<Tutores> findById(long id);
}
