package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.model.dto.DentistaDTO;
import com.dh.grupo05.clinica.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    DentistaRepository repository;

    public Dentista salvar(Dentista dentista) {
        return repository.save(dentista);
    }

    public List<DentistaDTO> buscarTodosDentistas() {
        List<Dentista> listDentista = repository.findAll();
        List<DentistaDTO> listDentistaDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Dentista d : listDentista){
            listDentistaDTO.add(mapper.convertValue(d, DentistaDTO.class));
        }
        return listDentistaDTO;
    }

    public Optional<Dentista> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public void modificar(Dentista dentista) {
        repository.saveAndFlush(dentista);
    }

}
