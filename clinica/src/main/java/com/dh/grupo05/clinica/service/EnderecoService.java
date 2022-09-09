package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.model.Endereco;
import com.dh.grupo05.clinica.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;

    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<Endereco> buscarTodosEndercos() {
        return repository.findAll();
    }

    public Optional<Endereco> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public void modificar(Endereco endereco) throws SQLException {
        repository.saveAndFlush(endereco);
    }
}
