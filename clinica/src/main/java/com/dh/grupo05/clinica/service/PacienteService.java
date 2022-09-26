package com.dh.grupo05.clinica.service;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
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

    public PacienteDTO buscaPorId(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> pacienteOptional = repository.findById(id);
        PacienteDTO pacienteDTO = null;
        try{
            Paciente paciente = pacienteOptional.get();
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Erro ao buscar o paciente, este paciente não existe");
        }
        return pacienteDTO;
    }

    public void modificar(Paciente paciente) {
        repository.saveAndFlush(paciente);
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Erro ao tentar excluir paciente, o paciente informado não existe"));
        repository.deleteById(id);
    }

    public List<Paciente> buscarPorCep(String cep){
        return repository.findByEnderecoCep(cep);
    }



}
