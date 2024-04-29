package com.developers.developers.Service;

import com.developers.developers.Repository.CitasRepository;

public interface CitasService {


    Citas save(Citas citas);

    <Optional> Citas findById(Long id);

}
