package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.model.Consulta;
import com.dh.grupo05.clinica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository repository;

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    public List<Consulta> buscarTodasConsultas() {
        return repository.findAll();
    }

    public Optional<Consulta> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public void modificar(Consulta consulta) {
        repository.saveAndFlush(consulta);
    }
}
