package com.developers.developers.Service;

import com.developers.developers.model.entity.Students;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    <Optional> Students findById(Long id);

    List<Students> findAll();

    Students save(Students students);
}
