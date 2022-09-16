package com.dh.grupo05.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name= "servicoPrestado")
    private String servicoPrestado;

    @Column(name= "dataConsulta")
    private Date dataConsulta;

    @ManyToOne
    @JoinColumn(name= "nomeDentista")
    private Dentista dentista;

    @ManyToOne
    @JoinColumn(name= "nomePaciente")
    private Paciente paciente;


}
