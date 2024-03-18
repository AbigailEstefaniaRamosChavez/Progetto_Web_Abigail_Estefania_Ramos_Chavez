package com.webappbackend.controllers;

import com.webappbackend.apiModels.Request.RegisterRequest;
import com.webappbackend.servizi.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*

 */
@RestController
@RequestMapping("/api/v0/autenticazione")
public class AutenticazioneController {

    UtenteService utenteService;

    @Autowired
    public AutenticazioneController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @PostMapping("/login")
    public void login(){
        //TODO
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest){
        utenteService.addUtente(registerRequest.toUtente());
        //TODO: controllare se l'utente è già registrato, tramite lo username
        return new ResponseEntity<>("utente registrato", HttpStatus.OK);
    }


}
