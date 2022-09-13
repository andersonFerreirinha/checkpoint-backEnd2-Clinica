package com.dh.grupo05.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name= "nome")
    private String nome;

    @Column(name= "sobrenome")
    private String sobrenome;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(name= "rg")
    private String rg;

    //para utilização de Date, seguir: "yyyy-MM-dd'T'HH:mm:ss.SSSX", "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"
    @Column(name= "dataCadastro")
    private Date dataCadastro;
}
