package com.developers.developers.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String last_Name;
    String maternal_Surname;
    String password;
    String lastName;
    String maternalSurname;
}
