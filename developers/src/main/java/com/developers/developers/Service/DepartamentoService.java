package com.developers.developers.Service;

import com.developers.developers.model.entity.Departamentos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartamentoService {

    List<Departamentos> findAll();
}
