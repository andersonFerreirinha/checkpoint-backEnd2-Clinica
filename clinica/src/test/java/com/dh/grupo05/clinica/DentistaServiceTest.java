package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.service.DentistaService;
import org.h2.command.dml.Delete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class DentistaServiceTest {

    @Autowired
    DentistaService service;

    static Dentista dentista;
    static Dentista dentista2;

    @BeforeAll
    static void doBefore(){
        dentista = new Dentista();
        dentista.setNome("Paulo");
        dentista.setSobrenome("Ferracini");
        dentista.setMatricula("54165161");

        dentista2 = new Dentista();
        dentista2.setNome("José");
        dentista2.setSobrenome("Silvério");
        dentista2.setMatricula("8674513");


    }

    @Test
    void conluindoSalvamento(){
        Dentista dentista1 = new Dentista();
        dentista1 = service.salvar(dentista);

        Assertions.assertNotNull(dentista.getId());
        System.out.println("Dentista1 foi salvo com sucesso");
    }

    @Test
    void buscarTodos(){
        Dentista dentista1 = new Dentista();
        dentista1 = service.salvar(dentista);

        Dentista dentista3 = new Dentista();
        dentista3 = service.salvar(dentista2);

        int tamanhoLista = service.buscarTodosDentistas().size();

        Assertions.assertEquals(2, tamanhoLista);
        System.out.println("O tamanho do Array é compatível ao esperado");
        }
}
