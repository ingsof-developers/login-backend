package com.developers.developers.Service;

import com.developers.developers.Repository.CitasRepository;
import com.developers.developers.model.entity.Citas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitasServiceImpl implements CitasService{

    @Autowired
    CitasRepository citasRepository;


    @Override
    public <Optional> Citas findById(Long id) {
        return citasRepository.findById(id).orElse(null);
    }


    @Override
    public Citas save(Citas citas) {
        return citasRepository.save(citas);
    }




}
