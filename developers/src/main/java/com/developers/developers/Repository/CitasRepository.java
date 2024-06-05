package com.developers.developers.Repository;

import com.developers.developers.model.entity.Citas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CitasRepository extends JpaRepository<Citas, Long> {

    Collection<Object> findByTutoresId(Long id);
    Collection<Object> findByStudentsId(Long id);

    Collection<Object> findByStudentsIdAndActiveIsTrue(Long id);
    Collection<Object> findByTutoresIdAndActiveIsTrue(Long id);
}
