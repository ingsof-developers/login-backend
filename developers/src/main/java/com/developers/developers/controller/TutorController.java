package com.developers.developers.controller;

import com.developers.developers.Service.TutorService;
import com.developers.developers.model.entity.Tutores;
import com.developers.developers.model.entity.dto.AuthResponse;
import com.developers.developers.model.entity.dto.RegisterRequest;
import com.developers.developers.model.entity.dto.TutorRegisterRequest;
import com.developers.developers.model.entity.dto.TutorUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody TutorRegisterRequest tutorRegisterRequest)
    {
        return ResponseEntity.ok(tutorService.save(tutorRegisterRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(tutorService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tutorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TutorUpdateRequest tutorUpdateRequest) {
        return ResponseEntity.ok(tutorService.update(id, tutorUpdateRequest));
    }
}
