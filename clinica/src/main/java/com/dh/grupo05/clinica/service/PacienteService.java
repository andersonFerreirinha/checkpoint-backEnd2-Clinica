package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.model.Paciente;
import com.dh.grupo05.clinica.model.dto.PacienteDTO;
import com.dh.grupo05.clinica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public List<PacienteDTO> buscarTodosPacientes() {
        List<Paciente>listPaciente = repository.findAll();
        List<PacienteDTO> listPacienteDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Paciente p : listPaciente){
            listPacienteDTO.add(mapper.convertValue(p, PacienteDTO.class));
        }
        return listPacienteDTO;
    }

    public Optional<Paciente> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public void modificar(Paciente paciente) {
        repository.saveAndFlush(paciente);
    }


    //criar método excluir
}
