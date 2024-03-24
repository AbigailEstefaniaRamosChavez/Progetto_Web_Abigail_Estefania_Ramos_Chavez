package com.webappbackend.controllers;

import com.webappbackend.apiModels.Request.LoginRequest;
import com.webappbackend.apiModels.Request.RegisterRequest;
import com.webappbackend.apiModels.Responses.JwtResponse;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*

 */
@RestController
@RequestMapping("/api/v0/autenticazione")
public class AutenticazioneController {

    AuthService authService;

    @Autowired
    public AutenticazioneController(AuthService authService) {
        this.authService = authService;
    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String token = authService.login(loginRequest.username, loginRequest.password);
        if(token == null)
            return new ResponseEntity<>("username o password errati", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest){
        Utente utente = registerRequest.toUtente();
        String token = authService.register(utente);
        if(token == null)
            return new ResponseEntity<>("l'utente è già registrato", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(new JwtResponse(token));
    }


}
