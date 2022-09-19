package com.dh.grupo05.clinica.controller;

import com.dh.grupo05.clinica.model.Endereco;
import com.dh.grupo05.clinica.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService service;

    @PostMapping
    public Endereco salvarEndereco(@RequestBody Endereco endereco) throws SQLException {
        return service.salvar(endereco);
    }

    @GetMapping
    public ResponseEntity buscarTodosEnderecos() throws SQLException {
        List<Endereco> enderecoList = service.buscarTodosEndercos();
        if(enderecoList.isEmpty()){
            return new ResponseEntity("Nenhum endereço encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(enderecoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaId",  method = RequestMethod.GET)
    public ResponseEntity buscaPorId(@RequestParam("id") Long id) throws SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Endereco> enderecoOptional = service.buscaPorId(id);
        if (enderecoOptional.isEmpty()) {
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }
        Endereco endereco = enderecoOptional.get();
        return new ResponseEntity(endereco, HttpStatus.OK);
    }

    @PatchMapping
    public void modificar(@RequestBody Endereco endereco) throws SQLException{
        service.modificar(endereco);
    }
}
