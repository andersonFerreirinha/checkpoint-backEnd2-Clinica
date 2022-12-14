package com.dh.grupo05.clinica.model.dto;

import com.dh.grupo05.clinica.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
//Dados sensiveis foram retirados
public class PacienteDTO {
    private String nome;
    private String sobrenome;
    private Endereco endereco;

}
