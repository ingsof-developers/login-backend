package com.developers.developers.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorRegisterRequest {
    String username;
    String password;
    String lastName;
    String maternalSurname;
    String telefono;
    Departamentos departamentoId;
}
