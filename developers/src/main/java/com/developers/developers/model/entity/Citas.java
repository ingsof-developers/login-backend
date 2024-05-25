package com.developers.developers.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "citas")
@Getter
@Setter

public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private Time hora;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutores tutores;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamentos departamentos;
}
