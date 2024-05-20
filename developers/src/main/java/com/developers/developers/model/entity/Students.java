package com.developers.developers.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String correo;
    private String telefono;

    @OneToOne
    @JoinColumn(name="user_id", unique = true)
    private UserEntity user;
}
