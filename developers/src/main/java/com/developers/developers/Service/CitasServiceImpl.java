package com.developers.developers.Service;

import com.developers.developers.Repository.CitasRepository;

public class CitasServiceImpl implements CitasService{

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
