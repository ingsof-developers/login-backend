package com.developers.developers.controller;

import com.developers.developers.Service.CitasService;
import com.developers.developers.model.entity.Citas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitasController {
    @Autowired
    CitasService citasService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Citas citas) {
        try {
            citasService.save(citas);
            return ResponseEntity.ok().body(citas);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
