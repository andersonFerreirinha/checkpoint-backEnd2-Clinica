package com.dh.grupo05.clinica.config.security;


import com.dh.grupo05.clinica.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    @Value("${ecommerce.jwt.expiration}")
    private String expiration;

    @Value("{ecommerce.jwt.secret}")
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

    public boolean verificaToken(String token){
        try{
            System.out.println(token);
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

}
