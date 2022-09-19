package com.dh.grupo05.clinica.controller;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.model.dto.DentistaDTO;
import com.dh.grupo05.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")

public class DentistaController {
    @Autowired
    DentistaService service;

    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody Dentista dentista) {
        return new ResponseEntity(service.salvar(dentista), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodosDentistas() {
        List<DentistaDTO> dentistaList = service.buscarTodosDentistas();
        if(dentistaList.isEmpty()){
            return new ResponseEntity("Nenhum dentista encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dentistaList, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaId", method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscaPorId(id), HttpStatus.OK);
    }

    @PatchMapping
    public void modificar(@RequestBody Dentista dentista) {
        service.modificar(dentista);
    }


}
