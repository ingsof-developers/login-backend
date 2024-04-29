package com.developers.developers.Service;

import com.developers.developers.Repository.CitasRepository;
import com.developers.developers.model.entity.Citas;
import org.springframework.stereotype.Service;

@Service
public interface CitasService {


    Citas save(Citas citas);

    <Optional> Citas findById(Long id);

}
