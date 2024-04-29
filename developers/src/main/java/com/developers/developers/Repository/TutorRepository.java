package com.developers.developers.Repository;

import com.developers.developers.model.entity.Tutores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutores, Long> {
}
