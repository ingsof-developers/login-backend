package com.developers.developers.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitasDTO {
    private Long id;
    private Date fecha;
    private Time hora;
    private String descripcion;
    private Long studentId;
    private Long tutorId;
    private String tutorName;
    private Long departamentoId;
    private String departamentoName;
}
