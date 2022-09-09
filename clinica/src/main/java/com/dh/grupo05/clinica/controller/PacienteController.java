package com.dh.grupo05.clinica.controller;

import com.dh.grupo05.clinica.model.Paciente;
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
    public Paciente salvarPaciente(@RequestBody Paciente paciente) {
        return service.salvar(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodosPacientes() {
        return service.buscarTodosPacientes();
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Paciente> pacienteOptional = service.buscaPorId(id);
        if(pacienteOptional.isEmpty()) {
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        Paciente paciente = pacienteOptional.get();

        return new ResponseEntity(paciente,HttpStatus.OK);
    }

    @PatchMapping
    public void modificar(@RequestBody Paciente paciente) {
        service.modificar(paciente);
    }


}
