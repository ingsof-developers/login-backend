package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.CitasRepository;

import com.developers.developers.Repository.DepartamentosRepository;
import com.developers.developers.Repository.StudentRepository;
import com.developers.developers.Repository.TutorRepository;
import com.developers.developers.Service.CitasService;
import com.developers.developers.model.entity.Citas;
import com.developers.developers.model.entity.Students;
import com.developers.developers.model.entity.Tutores;
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

    @Override
    public void deleteById(Long id) {

        citasRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        citasRepository.deleteAll();
    }

    @Override
    public Citas update(Citas citas) throws Exception {
        // Buscar la cita existente
        Citas existingCita = citasRepository.findById(citas.getId()).orElseThrow(() -> new Exception("Cita with id-> " + citas.getId() + " does not exists"));

        // Actualizar los campos necesarios
        existingCita.setFecha(citas.getFecha());
        existingCita.setHora(citas.getHora());
        existingCita.setDescripcion(citas.getDescripcion());
        existingCita.setStudents(citas.getStudents());
        existingCita.setTutores(citas.getTutores());
        existingCita.setDepartamentos(citas.getDepartamentos());
        existingCita.setActive(citas.getActive());

        // Guardar y devolver la cita actualizada
        return citasRepository.save(existingCita);
    }

    @Override
    public List<CitasDTO> findByTutorId(String email) {
        Tutores tutores = (Tutores) tutorRepository.findByCorreo(email).orElse(null);
        if(tutores == null) {
            return null;
        }
        Long studentId = tutores.getId();
        return citasRepository.findByTutoresId(studentId)
                .stream()
                .map((Object cita) -> convertToDTO((Citas) cita))
                .collect(Collectors.toList());
    }

    @Override
    public List<CitasDTO> findByStudentId(String email) {
        Students student = (Students) studentRepository.findByCorreo(email).orElse(null);
        if(student == null) {
            return null;
        }
        Long id = student.getId();
        return citasRepository.findByStudentsId(id)
                .stream()
                .map((Object cita) -> convertToDTO((Citas) cita))
                .collect(Collectors.toList());
    }

    @Override
    public List<CitasDTO> findByTutorIdAndActiveTrue(String email) {
        Students student = (Students) studentRepository.findByCorreo(email).orElse(null);
        if(student == null) {
            return null;
        }
        Long id = student.getId();
        return citasRepository.findByTutoresIdAndActiveIsTrue(id)
                .stream()
                .map((Object cita) -> convertToDTO((Citas) cita))
                .collect(Collectors.toList());
    }

    @Override
    public List<CitasDTO> findByStudentIdAndActiveTrue(String email) {
        Tutores tutor = (Tutores) tutorRepository.findByCorreo(email).orElse(null);
        if(tutor == null) {
            return null;
        }
        Long id = tutor.getId();
        return citasRepository.findByStudentsIdAndActiveIsTrue(id)
                .stream()
                .map((Object cita) -> convertToDTO((Citas) cita))
                .collect(Collectors.toList());
    }

    @Override
    public void deactivate(Long id) {
        Citas cita = citasRepository.findById(id).orElse(null);
        if(cita != null) {
            cita.setActive(false);
            citasRepository.save(cita);
        }
    }

    @Override
    public void activate(Long id) {
        Citas cita = citasRepository.findById(id).orElse(null);
        if(cita != null) {
            cita.setActive(true);
            citasRepository.save(cita);
        }
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
        dto.setActive(cita.getActive());

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

        citas.setActive(true);

        return citasRepository.save(citas);
    }

    @Override
    public <Optional> Citas findById(Long id) {
        return null;
    }


}
