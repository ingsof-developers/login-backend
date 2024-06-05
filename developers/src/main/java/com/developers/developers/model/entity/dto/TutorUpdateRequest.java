package com.developers.developers.model.entity.dto;

import com.developers.developers.model.entity.Departamentos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorUpdateRequest {
    String username;
    String password;
    String lastName;
    String maternalSurname;
    String telefono;
    String correo;

}
