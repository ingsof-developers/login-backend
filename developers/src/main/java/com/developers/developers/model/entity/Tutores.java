package com.developers.developers.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutores")
@Getter 
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tutores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String telefono;
    private String correo;

    // Primera relacion con Tabla departamentos
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamentos departamentoId;

    // Segunda relacion con Tabla users
    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private UserEntity user;
}
