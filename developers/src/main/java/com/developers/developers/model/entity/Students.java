package com.developers.developers.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String telefono;

    @OneToOne
    @JoinColumn(name="user_id", unique = true)
    private UserEntity user;
}
