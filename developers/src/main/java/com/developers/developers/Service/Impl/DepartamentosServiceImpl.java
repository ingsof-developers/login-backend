package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.DepartamentosRepository;
import com.developers.developers.Service.DepartamentoService;
import com.developers.developers.model.entity.Departamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentosServiceImpl implements DepartamentoService {

    @Autowired
    DepartamentosRepository departamentosRepository;

    @Override
    public List<Departamentos> findAll() {
        List<Departamentos> list = departamentosRepository.findAll();

        if(list.isEmpty()) {
            throw new RuntimeException("No se encontraron departamentos");
        }else {
            return list;
        }
    }
}
