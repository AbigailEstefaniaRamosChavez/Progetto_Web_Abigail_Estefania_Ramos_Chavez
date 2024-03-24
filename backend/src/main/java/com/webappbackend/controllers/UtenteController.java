package com.webappbackend.controllers;

import com.webappbackend.apiModels.DTOs.DisegnoDto;
import com.webappbackend.apiModels.DTOs.UtenteDto;
import com.webappbackend.controllers.utils.DaiUtenteUtil;
import com.webappbackend.modelli.Disegno;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.UtenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/utente")
public class UtenteController {

    UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/")
        public ResponseEntity<Object> getUtente() {
        Utente utente = DaiUtenteUtil.DaiUtente(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        if(utente == null)
            return new ResponseEntity<>("l'utente non è stato trovato",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UtenteDto(utente), HttpStatus.OK);
    }

    @Operation(summary = "Prendere i disegni dell'utente")
    @GetMapping("/disegni")
    public ResponseEntity<Object> getDisegniUtente() {
        Utente utente = DaiUtenteUtil.DaiUtente(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        if(utente == null)
            return new ResponseEntity<>("l'utente non è stato trovato",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(
                utente.getDisegni()
                        .stream()
                        .map(DisegnoDto::new)
                , HttpStatus.OK
        );
    }


}