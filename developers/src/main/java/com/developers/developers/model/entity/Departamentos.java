package com.developers.developers.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departamentos")
@Data
public class Departamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String descripcion;


}
