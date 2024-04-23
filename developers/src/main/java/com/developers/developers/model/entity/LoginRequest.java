package com.developers.developers.model.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    String username;
    String password;


}