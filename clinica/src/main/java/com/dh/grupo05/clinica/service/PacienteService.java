package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.model.Paciente;
import com.dh.grupo05.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public List<Paciente> buscarTodosPacientes() {
        return repository.findAll();
    }

    public Optional<Paciente> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public void modificar(Paciente paciente) {
        repository.saveAndFlush(paciente);
    }

}
