package com.developers.developers.Service;

import com.developers.developers.model.entity.Students;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    Students save(Students students);
}
