package com.developers.developers.Service;

import com.developers.developers.Repository.TutorRepository;
import com.developers.developers.model.entity.Tutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImpl implements TutorService{

    @Autowired
    TutorRepository tutorRepository;

    @Override
    public <Optional> Tutores findById(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tutores> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutores save(Tutores tutores) {
        return tutorRepository.save(tutores);
    }
}
