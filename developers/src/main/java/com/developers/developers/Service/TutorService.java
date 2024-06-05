package com.developers.developers.Service;

import com.developers.developers.model.entity.dto.AuthResponse;
import com.developers.developers.model.entity.dto.TutorRegisterRequest;
import com.developers.developers.model.entity.Tutores;
import com.developers.developers.model.entity.dto.TutorUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TutorService {

    <Optional> Tutores findById(Long id);
    List<Tutores> findAll();

    AuthResponse save(TutorRegisterRequest tutorRegisterRequest);

    void delete(Long id);

    AuthResponse update(Long id, TutorUpdateRequest tutorUpdateRequest);
}
