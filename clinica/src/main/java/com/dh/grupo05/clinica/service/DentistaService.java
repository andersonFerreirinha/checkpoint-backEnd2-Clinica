package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    DentistaRepository repository;

    public Dentista salvar(Dentista dentista) {
        return repository.save(dentista);
    }

    public List<Dentista> buscarTodosDentistas() {
        return repository.findAll();
    }

    public Optional<Dentista> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public void modificar(Dentista dentista) {
        repository.saveAndFlush(dentista);
    }

}
