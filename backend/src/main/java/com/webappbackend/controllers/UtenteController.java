package com.webappbackend.controllers;

import com.webappbackend.apiModels.DTOs.UtenteDto;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/utente")
public class UtenteController {

    UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/{id}")
        public ResponseEntity<Object> getUtente(@PathVariable("id") int id) {
        Utente utente = utenteService.getUtente(id);
        if(utente == null)
            return new ResponseEntity<>("l'utente non è stato trovato",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UtenteDto(utente), HttpStatus.OK);
    }

    /*
     * dato un utente dammi tutti i disegni dell'utente(tramite solo l'id passato nella url)
     * richiesta in GET
     *
     */
    @GetMapping("/disegni/{id}")
    public ResponseEntity<Object> getDisegniUtente(@PathVariable("id") int id) {
        Utente utente = utenteService.getUtente(id);
        if(utente == null)
            return new ResponseEntity<>("l'utente non è stato trovato",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(utente.getDisegni(), HttpStatus.OK);
    }


}