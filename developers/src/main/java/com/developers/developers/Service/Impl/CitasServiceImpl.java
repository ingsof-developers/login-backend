package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.CitasRepository;

import com.developers.developers.Repository.DepartamentosRepository;
import com.developers.developers.Repository.StudentRepository;
import com.developers.developers.Repository.TutorRepository;
import com.developers.developers.Service.CitasService;
import com.developers.developers.model.entity.Citas;
import com.developers.developers.model.entity.dto.CitasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CitasDTO> findAll() {
        List<Citas> citas = citasRepository.findAll();
        return citas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CitasDTO convertToDTO(Citas cita) {
        CitasDTO dto = new CitasDTO();
        dto.setId(cita.getId());
        dto.setFecha(cita.getFecha());
        dto.setHora(cita.getHora());
        dto.setDescripcion(cita.getDescripcion());
        dto.setStudentId(cita.getStudents().getId());
        dto.setTutorId(cita.getTutores().getId());
        dto.setTutorName(cita.getTutores().getName());
        dto.setDepartamentoId(cita.getDepartamentos().getId());
        dto.setDepartamentoName(cita.getDepartamentos().getName());

        return dto;
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

    @Override
    public <Optional> Citas findById(Long id) {
        return null;
    }


}
