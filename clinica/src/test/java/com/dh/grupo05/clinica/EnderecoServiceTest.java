package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Consulta;
import com.dh.grupo05.clinica.model.Endereco;
import com.dh.grupo05.clinica.service.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.SQLException;

@SpringBootTest
@Transactional
public class EnderecoServiceTest {

    @Autowired
    EnderecoService service;

    static Endereco enderecoMolde1;

    static Endereco enderecoMolde2;

    static Endereco enderecoMolde3;

    @BeforeAll
    static void doBefore() {
        enderecoMolde1 = new Endereco();
        enderecoMolde1.setRua("Rua das Couves");

        enderecoMolde2 = new Endereco();
        enderecoMolde2.setRua("Rua das Flores");

        enderecoMolde3 = new Endereco();
        enderecoMolde3.setRua("Av Ipiranga");
    }

    @Test
    void conluindoSalvamento() {
        Endereco endereco1 = new Endereco();
        endereco1 = service.salvar(enderecoMolde1);

        Assertions.assertNotNull(endereco1.getId(), "Endereco1 foi salvo com sucesso");
    }

    @Test
    void buscarTodos(){
        Endereco endereco2 = new Endereco();
        endereco2 = service.salvar(enderecoMolde2);

        Endereco endereco3 = new Endereco();
        endereco3 = service.salvar(enderecoMolde3);

        int tamanhoLista = service.buscarTodosEndercos().size();

        Assertions.assertEquals(2, tamanhoLista, "O tamanho do Array é compatível ao esperado");
    }

    @Test
    void buscarPorId() throws ResourceNotFoundException {
        Endereco endereco4 = new Endereco();
        endereco4 = service.salvar(enderecoMolde3);

        Long idEndereco4 = endereco4.getId();

        String nomeRua = service.buscaPorId(idEndereco4).get().getRua();

        System.out.println("O número do Id da consulta adicionada é: "+ idEndereco4);
        System.out.println("O serviço da consulta salva é: "+ nomeRua);

        Assertions.assertEquals("Av Ipiranga", nomeRua, "A rua consultada é compatível ao esperado ");


        // ------------- segundo teste --------------
        Endereco endereco5 = new Endereco();
        endereco5 = service.salvar(enderecoMolde1);

        Long idEndereco5 = endereco5.getId();

        String nomeRua2 = service.buscaPorId(idEndereco5).get().getRua();

        System.out.println("O número do Id da consulta adicionada é: "+ idEndereco5);
        System.out.println("O serviço da consulta salva é: "+ nomeRua2);

        Assertions.assertEquals("Rua das Couves", nomeRua2, "A rua consultada é compatível ao esperado ");

    }

    @Test
    void modificar() throws SQLException {
        Endereco endereco6 = new Endereco();
        endereco6 = service.salvar(enderecoMolde2);
        System.out.println("O nome do da rua é: "+ endereco6.getRua());

        Assertions.assertEquals("Rua das Flores", endereco6.getRua(), "A rua consultada é compatível ao esperado ");
        endereco6.setRua("Av Paulista");
        service.modificar(endereco6);

        System.out.println("O novo nome da rua é: "+ endereco6.getRua());
        Assertions.assertEquals("Av Paulista", endereco6.getRua(), "A rua consultada é compatível ao esperado ");
    }
}
