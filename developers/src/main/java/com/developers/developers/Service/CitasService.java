package com.developers.developers.Service;

import com.developers.developers.Repository.CitasRepository;
import com.developers.developers.model.entity.Citas;

import com.developers.developers.model.entity.dto.CitasDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CitasService {


    Citas save(Citas citas) throws Exception;

    <Optional> Citas findById(Long id);

    List<CitasDTO> findAll();

    void deleteById(Long id);

    void deleteAll();
    Citas update(Citas citas) throws Exception;

    List<CitasDTO> findByTutorId(String email);

    List<CitasDTO> findByStudentId(String email);

    List<CitasDTO> findByTutorIdAndActiveTrue(String email);

    List<CitasDTO> findByStudentIdAndActiveTrue(String email);

    void deactivate(Long id);

    void activate(Long id);
}
