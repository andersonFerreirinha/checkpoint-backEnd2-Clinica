package com.dh.grupo05.clinica.config.security;

import com.dh.grupo05.clinica.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    AutenticacaoViaTokenFilter autenticacaoViaTokenFilter;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/consulta").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/consulta").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/consulta").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/endereco").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/endereco").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/endereco").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/dentista").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/dentista").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/paciente").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/paciente").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/paciente").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/consulta").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(autenticacaoViaTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
