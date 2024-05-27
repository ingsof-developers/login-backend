package com.developers.developers.controller;

import com.developers.developers.Service.DepartamentoService;
import com.developers.developers.model.entity.Departamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentosController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll()
    {
        List<Departamentos> list = departamentoService.findAll();
        return ResponseEntity.ok(list);
    }
}
