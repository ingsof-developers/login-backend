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

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(citasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(citasService.findById(id));
    }

    @GetMapping("/tutor/{email}")
    public ResponseEntity<?> getByTutorId(@PathVariable String email) {
        return ResponseEntity.ok().body(citasService.findByTutorId(email));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll() {
        citasService.deleteAll();
        return ResponseEntity.ok().body("All citas deleted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        citasService.deleteById(id);
        return ResponseEntity.ok().body("Cita with id: " + id + " deleted");
    }

    @GetMapping("/student/{email}")
    public ResponseEntity<?> getByStudentId(@PathVariable String email) {
        return ResponseEntity.ok().body(citasService.findByStudentId(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Citas citas) {
        try {
            Citas updatedCita = citasService.update(citas);
            return ResponseEntity.ok().body(updatedCita);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        try {
            citasService.activate(id);
            return ResponseEntity.ok().body("Cita with id: " + id + " activated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        try {
            citasService.deactivate(id);
            return ResponseEntity.ok().body("Cita with id: " + id + " deactivated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/active/{email}")
    public ResponseEntity<?> getByStudentIdAndActiveTrue(@PathVariable String email) {
        return ResponseEntity.ok().body(citasService.findByStudentIdAndActiveTrue(email));
    }

    @GetMapping("/tutor/active/{email}")
    public ResponseEntity<?> getByTutorIdAndActiveTrue(@PathVariable String email) {
        return ResponseEntity.ok().body(citasService.findByTutorIdAndActiveTrue(email));
    }
}
