package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.StudentRepository;
import com.developers.developers.Service.StudentService;
import com.developers.developers.model.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;


    @Override
    public Students save(Students students) {
        return studentRepository.save(students);
    }

    @Override
    public <Optional> Students findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Students> findAll() {
        return studentRepository.findAll();
    }
}
