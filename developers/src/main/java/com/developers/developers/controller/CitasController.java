package com.developers.developers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitasController {

    CitasService citasService;

    @PostMapping
    public ResponseEntity<?> create()
    {
        return new ResponseEntity<>().ok().build();
    }

    @GetMapping


    @GetMapping(/{id})

    @PutMapping

    @DeleteMapping
}
