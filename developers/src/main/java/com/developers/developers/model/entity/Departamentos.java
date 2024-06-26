package com.developers.developers.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "departamentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Departamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "description_info")
    private String descriptionInfo;

    @Column(name = "dates")
    private String dates;

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Service> services;

    public Departamentos(Long id) {
        this.id = id;
    }

}
