package com.dh.grupo05.clinica.controller;

import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.model.dto.DentistaDTO;
import com.dh.grupo05.clinica.service.DentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService service;

    @PostMapping
    public Dentista salvarDentista(@RequestBody Dentista dentista) {
        return service.salvar(dentista);
    }

    @GetMapping
    public List<Dentista> buscarTodosDentistas() {
        return service.buscarTodosDentistas();
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentistaOptional = service.buscaPorId(id);
        if (dentistaOptional.isEmpty()) {
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }
        Dentista dentista = dentistaOptional.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
        return new ResponseEntity(dentistaDTO, HttpStatus.OK);
    }

    @PatchMapping
    public void modificar(@RequestBody Dentista dentista) {
        service.modificar(dentista);
    }


}
