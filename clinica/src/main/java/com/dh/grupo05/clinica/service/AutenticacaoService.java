package com.dh.grupo05.clinica.service;


import com.dh.grupo05.clinica.model.Usuario;
import com.dh.grupo05.clinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Usuario usuario = repository.findByUsername(username);
            return usuario;
        }catch (UsernameNotFoundException exception) {
            throw new UsernameNotFoundException("Usuario não existe");
        }
    }


}
