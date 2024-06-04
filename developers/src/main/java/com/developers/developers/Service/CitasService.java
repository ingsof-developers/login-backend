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

}
