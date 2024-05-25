package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.CitasRepository;

import com.developers.developers.Repository.DepartamentosRepository;
import com.developers.developers.Repository.StudentRepository;
import com.developers.developers.Repository.TutorRepository;
import com.developers.developers.Service.CitasService;
import com.developers.developers.model.entity.Citas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    CitasRepository citasRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    DepartamentosRepository departamentosRepository;


    @Override
    public <Optional> Citas findById(Long id) {
        return citasRepository.findById(id).orElse(null);
    }


    @Override
    public Citas save(Citas citas) throws Exception {
        if(studentRepository.findById(citas.getStudents().getId()) == null) {
            throw new Exception("Student with id-> " + citas.getStudents().getId() + " does not exists");
        }

        if(tutorRepository.findById(citas.getTutores().getId()) == null) {
            throw new Exception("Tutor with id-> " + citas.getTutores().getId() + " does not exists");
        }

        if(departamentosRepository.findById(citas.getDepartamentos().getId()) == null) {
            throw new Exception("Departament with id-> " + citas.getDepartamentos().getId() + " does not exists");
        }
        return citasRepository.save(citas);
    }




}
