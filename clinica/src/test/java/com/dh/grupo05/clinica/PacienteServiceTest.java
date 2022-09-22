package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.exception.ResourceNotFoundException;
import com.dh.grupo05.clinica.model.Consulta;
import com.dh.grupo05.clinica.model.Paciente;
import com.dh.grupo05.clinica.service.ConsultaService;
import com.dh.grupo05.clinica.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PacienteServiceTest {

    @Autowired
    PacienteService service;

    static Paciente pacienteMolde1;

    static Paciente pacienteMolde2;

    static Paciente pacienteMolde3;

    static Paciente pacienteMolde4;

    @BeforeAll
    static void doBefore() {
        pacienteMolde1 = new Paciente();
        pacienteMolde1.setNome("Pabllo");

        pacienteMolde2 = new Paciente();
        pacienteMolde2.setNome("Anitta");

        pacienteMolde3 = new Paciente();
        pacienteMolde3.setNome("Gloria");

        pacienteMolde4 = new Paciente();
        pacienteMolde4.setNome("Donatella");
    }

    @Test
    void conluindoSalvamento() {
        Paciente paciente1 = new Paciente();
        paciente1 = service.salvar(pacienteMolde1);

        Assertions.assertNotNull(paciente1.getId(), "Paciente1 foi salva com sucesso");
    }

    @Test
    void buscarTodos(){
        Paciente paciente2 = new Paciente();
        paciente2 = service.salvar(pacienteMolde2);

        Paciente paciente3 = new Paciente();
        paciente3 = service.salvar(pacienteMolde3);

        int tamanhoLista = service.buscarTodosPacientes().size();

        Assertions.assertEquals(2, tamanhoLista, "O tamanho do Array é compatível ao esperado");
    }

    @Test
    void buscarPorId() throws ResourceNotFoundException {
        Paciente paciente4 = new Paciente();
        paciente4 = service.salvar(pacienteMolde1);

        Long idPaciente4 = paciente4.getId();

        String nomePaciente = service.buscaPorId(idPaciente4).getNome();

        System.out.println("O número do Id do paciente adicionado é: "+ idPaciente4);
        System.out.println("O nome do paciente salvo é: "+ nomePaciente);

        Assertions.assertEquals("Pabllo", nomePaciente, "O nome do paciente salvo é compatível ao esperado ");


        // ------------- segundo teste --------------
        Paciente paciente5 = new Paciente();
        paciente5 = service.salvar(pacienteMolde2);

        Long idPaciente5 = paciente5.getId();

        String nomePaciente2 = service.buscaPorId(idPaciente5).getNome();

        System.out.println("O número do Id do paciente adicionado é: "+ idPaciente5);
        System.out.println("O nome do paciente salvo é: "+ nomePaciente2);

        Assertions.assertEquals("Anitta", nomePaciente2, "O nome do paciente salvo é compatível ao esperado");
    }

    @Test
    void modificar() {
        Paciente paciente6 = new Paciente();
        paciente6 = service.salvar(pacienteMolde3);
        System.out.println("O nome do serviço a ser prestado é: "+ paciente6.getNome());

        Assertions.assertEquals("Gloria", paciente6.getNome(), "O nome do paciente salvo é compatível ao esperado ");
        paciente6.setNome("Daiane");
        service.modificar(paciente6);

        System.out.println("O novo nome do serviço a ser prestado é: "+ paciente6.getNome());
        Assertions.assertEquals("Daiane", paciente6.getNome(), "O nome do paciente salvo é compatível ao esperado ");
    }

    @Test
    void excluir() throws ResourceNotFoundException {
        Paciente paciente7 = new Paciente();
        paciente7 = service.salvar(pacienteMolde4);

        service.excluir(paciente7.getId());

        Paciente finalPaciente = paciente7;

        ResourceNotFoundException e = assertThrows(
                ResourceNotFoundException.class, () -> service.buscaPorId(finalPaciente.getId()));

                assertFalse(e.getMessage().contains("Erro ao tentar excluir paciente, o paciente informado não existe"));
    }

}