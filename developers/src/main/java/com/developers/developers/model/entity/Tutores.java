package com.developers.developers.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tutores")
@Getter
@Setter

public class Tutores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String telefono;

    // Primera relacion con Tabla departamentos
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamentos departamentos;

    // Segunda relacion con Tabla users
    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private UserEntity users;
}
