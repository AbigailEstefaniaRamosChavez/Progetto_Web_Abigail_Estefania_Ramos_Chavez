package com.webappbackend.servizi;

import com.webappbackend.modelli.Utente;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    UtenteService utenteService;
    JwtService jwtService;
    PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UtenteService utenteService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.utenteService = utenteService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }



    public String login(String username, String password){
        Utente utente = utenteService.getUtente(username);
        if(utente != null && passwordEncoder.matches(password+utente.salt, utente.password)){
            return jwtService.DaiToken(utente.id);
        }
        return null;
    }


    public String register(Utente utente){
        if(utenteService.getUtente(utente.username) != null)
            return null;
        byte[] bytes = new byte[16];
        new Random().nextBytes(bytes);
        String salt = Encoders.BASE64.encode(bytes);
        utente.password = passwordEncoder.encode(
                utente.password+
                salt);
        utente.salt = salt;
        utenteService.addUtente(utente);
        return jwtService.DaiToken(utente.id);
    }
}
