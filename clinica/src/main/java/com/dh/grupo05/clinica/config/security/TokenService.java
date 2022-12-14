package com.dh.grupo05.clinica.config.security;


import com.dh.grupo05.clinica.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    @Value("${clinica.jwt.expiration}")
    private String expiration;

    @Value("{clinica.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date dataHoje = new Date();
        Date dataExpiracao = new Date(dataHoje.getTime() + Long.parseLong(expiration));
        String token = Jwts.builder()
                .setIssuer("API DH Clinica")
                .setSubject(usuarioLogado.getUsername())
                .setIssuedAt(dataHoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public boolean verficaToken(String token){
        try{
            System.out.println(token);
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch(Exception exception){
            return false;
        }
    }

    public String getUsernameUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        String username =claims.getSubject();
        return username;
    }

}
