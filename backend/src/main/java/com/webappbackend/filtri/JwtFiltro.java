package com.webappbackend.filtri;

import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.JwtService;
import com.webappbackend.servizi.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFiltro extends OncePerRequestFilter {

    JwtService jwtService;
    UtenteService utenteService;
    @Autowired
    JwtFiltro(JwtService jwtService,UtenteService utenteService){
        this.jwtService = jwtService;
        this.utenteService = utenteService;
    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String jwt;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            }else {
                filterChain.doFilter(request, response);
                return;
            }
        int id = this.jwtService.DaiId(jwt);
        if(!this.jwtService.Valido(jwt)){
            filterChain.doFilter(request, response);
            return;
        }
        //mi assicuro che l'utente sia esistente
        Utente utente = utenteService.getUtente(id);
        if(utente != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if(this.jwtService.Valido(jwt)) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities()
                        ));

            }
        }
        filterChain.doFilter(request, response);
    }
}
