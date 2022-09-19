package com.dh.grupo05.clinica.controller;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Paciente;
import com.dh.grupo05.clinica.model.dto.PacienteDTO;
import com.dh.grupo05.clinica.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public ResponseEntity salvarPaciente(@RequestBody Paciente paciente) {
        Paciente salvarPaciente = service.salvar(paciente);
        return new ResponseEntity(salvarPaciente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodosPacientes() {
        List<PacienteDTO> pacienteDTOList = service.buscarTodosPacientes();
        if(pacienteDTOList.isEmpty()){
            return new ResponseEntity("Nenhum paciente encontrado", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(pacienteDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscaPorId(id), HttpStatus.OK);
    }

    @PatchMapping
    public void modificar(@RequestBody Paciente paciente) {
        service.modificar(paciente);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
    }


}
