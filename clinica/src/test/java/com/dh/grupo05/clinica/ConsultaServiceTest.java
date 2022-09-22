package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Consulta;
import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.service.ConsultaService;
import com.dh.grupo05.clinica.service.DentistaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
public class ConsultaServiceTest {

    @Autowired
    ConsultaService service;

    static Consulta consultaMolde1;

    static Consulta consultaMolde2;

    static Consulta consultaMolde3;

    @BeforeAll
    static void doBefore() {
        consultaMolde1 = new Consulta();
        consultaMolde1.setServicoPrestado("Limpeza");

        consultaMolde2 = new Consulta();
        consultaMolde2.setServicoPrestado("Obturação");

        consultaMolde3 = new Consulta();
        consultaMolde3.setServicoPrestado("Canal");
    }

    @Test
    void conluindoSalvamento() {
        Consulta consulta1 = new Consulta();
        consulta1 = service.salvar(consultaMolde1);

        Assertions.assertNotNull(consulta1.getId(), "Consulta1 foi salva com sucesso");
    }

    @Test
    void buscarTodos(){
        Consulta consulta2 = new Consulta();
        consulta2 = service.salvar(consultaMolde2);

        Consulta consulta3 = new Consulta();
        consulta2 = service.salvar(consultaMolde3);

        int tamanhoLista = service.buscarTodasConsultas().size();

        Assertions.assertEquals(2, tamanhoLista, "O tamanho do Array é compatível ao esperado");
    }

    @Test
    void buscarPorId() throws ResourceNotFoundException {
        Consulta consulta4 = new Consulta();
        consulta4 = service.salvar(consultaMolde1);

        Long idConsulta4 = consulta4.getId();

        String nomeServicoPrestado = service.buscaPorId(idConsulta4).get().getServicoPrestado();

        System.out.println("O número do Id da consulta adicionada é: "+ idConsulta4);
        System.out.println("O serviço da consulta salva é: "+ nomeServicoPrestado);

        Assertions.assertEquals("Limpeza", nomeServicoPrestado, "O serviço da consulta salva é compatível ao esperado ");


        // ------------- segundo teste --------------
        Consulta consulta5 = new Consulta();
        consulta5 = service.salvar(consultaMolde2);

        Long idConsulta5 = consulta5.getId();

        String nomeServicoPrestado2 = service.buscaPorId(idConsulta5).get().getServicoPrestado();

        System.out.println("O número do Id da consulta adicionada é: "+ idConsulta5);
        System.out.println("O serviço da consulta salva é: "+ nomeServicoPrestado2);
        Assertions.assertEquals("Obturação", nomeServicoPrestado2, "O serviço da consulta salva é compatível ao esperado ");

    }

    @Test
    void modificar() {
        Consulta consulta6 = new Consulta();
        consulta6 = service.salvar(consultaMolde3);
        System.out.println("O nome do serviço a ser prestado é: "+ consulta6.getServicoPrestado());

        Assertions.assertEquals("Canal", consulta6.getServicoPrestado(), "O serviço da consulta salva é compatível ao esperado ");
        consulta6.setServicoPrestado("Clareamento Dental");
        service.modificar(consulta6);

        System.out.println("O novo nome do serviço a ser prestado é: "+ consulta6.getServicoPrestado());
        Assertions.assertEquals("Clareamento Dental", consulta6.getServicoPrestado(), "O serviço da consulta salva é compatível ao esperado ");
    }

}