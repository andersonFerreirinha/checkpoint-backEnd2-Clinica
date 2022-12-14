package com.dh.grupo05.clinica;

import com.dh.grupo05.clinica.model.Perfil;
import com.dh.grupo05.clinica.model.Usuario;
import com.dh.grupo05.clinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioRepository repository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //Criando o nosso perfis
        Perfil perfilUser = new Perfil();
        Perfil perfilAdmin = new Perfil();
        perfilUser.setDescricao("USER");
        perfilAdmin.setDescricao("ADMIN");
        //Criando Lista de Perfil
        List<Perfil> perfilList1 = new ArrayList<>();
        List<Perfil> perfilList2 = new ArrayList<>();

        //Populando listas de perfis
        perfilList1.add(perfilUser);
        perfilList2.add(perfilAdmin);

        //Criando usuários
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        //Populando usuário
        usuario1.setPassword(encoder.encode("123456"));
        usuario1.setUsername("teste1");
        usuario1.setPerfis(perfilList1);

        //Populando usuario 2
        usuario2.setPassword(encoder.encode("1234567"));
        usuario2.setUsername("teste2");
        usuario2.setPerfis(perfilList2);

        repository.save(usuario1);
        repository.save(usuario2);


    }
}
