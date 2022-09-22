package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.model.dto.DentistaDTO;
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
import java.util.Optional;

@SpringBootTest
@Transactional
public class DentistaServiceTest {

    @Autowired
    DentistaService service;

    static Dentista dentista;
    static Dentista dentista2;
    static Dentista dentista3;

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

        dentista3 = new Dentista();
        dentista3.setNome("Ana");
        dentista3.setSobrenome("Fidalga");
        dentista3.setMatricula("5465456");


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
    @Test
    void buscarPorId() throws ResourceNotFoundException {
        Dentista dentista4 = new Dentista();
        dentista4 = service.salvar(dentista3);

        Long idDentista4 = dentista4.getId();
        String nomeDentistaBuscado = service.buscaPorId(idDentista4).getNome();

        System.out.println("O número do Id do dentista adicionado é: "+ idDentista4);
        System.out.println("O nome do dentista adicionado é: "+ nomeDentistaBuscado);

        Assertions.assertEquals("Ana", nomeDentistaBuscado);
    }

    @Test
    void modificar() {
        Dentista dentista5 = new Dentista();
        dentista5 = service.salvar(dentista);
        System.out.println("O nome do dentista adicionado é: "+ dentista5.getNome());

        Assertions.assertEquals("Paulo", dentista5.getNome());
        dentista5.setNome("Cher");
        service.modificar(dentista5);

        System.out.println("O novo nome do dentista adicionado é: "+ dentista5.getNome());
        Assertions.assertEquals("Cher", dentista5.getNome());
    }
}
