package com.dh.grupo05.clinica.controller;

import com.dh.grupo05.clinica.model.Consulta;
import com.dh.grupo05.clinica.service.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService service;

    @PostMapping
    public Consulta salvarConsulta(@RequestBody Consulta consulta){
        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodasConsultas(){
        return service.buscarTodasConsultas();
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Consulta> consultaOptional = service.buscaPorId(id);
        if (consultaOptional.isEmpty()) {
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }
        Consulta consulta = consultaOptional.get();

        return new ResponseEntity(consulta, HttpStatus.OK);
    }

    @PatchMapping
    public void modificar(@RequestBody Consulta consulta){
        service.modificar(consulta);
    }

}
