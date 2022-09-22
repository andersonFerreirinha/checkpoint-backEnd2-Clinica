package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Dentista;
import com.dh.grupo05.clinica.service.DentistaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class DentistaServiceTest {

    @Autowired
    DentistaService service;

    static Dentista dentistaMolde1;
    static Dentista dentistaMolde2;
    static Dentista dentistaMolde3;

    @BeforeAll
    static void doBefore(){
        dentistaMolde1 = new Dentista();
        dentistaMolde1.setNome("Paulo");
        dentistaMolde1.setSobrenome("Ferracini");
        dentistaMolde1.setMatricula("54165161");

        dentistaMolde2 = new Dentista();
        dentistaMolde2.setNome("José");
        dentistaMolde2.setSobrenome("Silvério");
        dentistaMolde2.setMatricula("8674513");

        dentistaMolde3 = new Dentista();
        dentistaMolde3.setNome("Ana");
        dentistaMolde3.setSobrenome("Fidalga");
        dentistaMolde3.setMatricula("5465456");


    }

    @Test
    void conluindoSalvamento(){
        Dentista dentista1 = new Dentista();
        dentista1 = service.salvar(dentistaMolde1);

        Assertions.assertNotNull(dentista1.getId(), "Dentista1 foi salvo com sucesso");

    }

    @Test
    void buscarTodos(){
        Dentista dentista1 = new Dentista();
        dentista1 = service.salvar(dentistaMolde1);

        Dentista dentista3 = new Dentista();
        dentista3 = service.salvar(dentistaMolde2);

        int tamanhoLista = service.buscarTodosDentistas().size();

        Assertions.assertEquals(2, tamanhoLista, "O tamanho do Array é compatível ao esperado");

    }
    @Test
    void buscarPorId() throws ResourceNotFoundException {
        Dentista dentista4 = new Dentista();
        dentista4 = service.salvar(dentistaMolde3);

        Long idDentista4 = dentista4.getId();
        String nomeDentistaBuscado = service.buscaPorId(idDentista4).getNome();

        System.out.println("O número do Id do dentista adicionado é: "+ idDentista4);
        System.out.println("O nome do dentista adicionado é: "+ nomeDentistaBuscado);

        Assertions.assertEquals("Ana", nomeDentistaBuscado,"O nome do dentista buscado é compatível ao esperado");
    }

    @Test
    void modificar() {
        Dentista dentista5 = new Dentista();
        dentista5 = service.salvar(dentistaMolde1);
        System.out.println("O nome do dentista adicionado é: "+ dentista5.getNome());

        Assertions.assertEquals("Paulo", dentista5.getNome(), "O nome do dentista buscado é compatível ao esperado");
        dentista5.setNome("Cher");
        service.modificar(dentista5);

        System.out.println("O novo nome do dentista adicionado é: "+ dentista5.getNome());
        Assertions.assertEquals("Cher", dentista5.getNome(), "O nome do dentista buscado é compatível ao esperado");
    }
}
