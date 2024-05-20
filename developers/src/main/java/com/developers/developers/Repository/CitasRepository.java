package com.developers.developers.Repository;

import com.developers.developers.model.entity.Citas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitasRepository extends JpaRepository<Citas, Long> {
}
