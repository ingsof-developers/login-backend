package com.developers.developers.Service;

import com.developers.developers.model.entity.Tutores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TutorService {

    <Optional> Tutores findById(Long id);
    List<Tutores> findAll();

    Tutores save(Tutores tutores);
}
