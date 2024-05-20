package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.CitasRepository;
import com.developers.developers.Service.CitasService;
import com.developers.developers.model.entity.Citas;

public class CitasServiceImpl implements CitasService {

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
