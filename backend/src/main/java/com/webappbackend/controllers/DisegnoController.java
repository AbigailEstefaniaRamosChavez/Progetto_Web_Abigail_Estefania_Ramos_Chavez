package com.webappbackend.controllers;

import com.webappbackend.apiModels.Request.DisegnoAggiungiRequest;
import com.webappbackend.apiModels.Request.DisegnoModificaRequest;
import com.webappbackend.modelli.Disegno;
import com.webappbackend.servizi.DisegnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/disegno")
public class DisegnoController {
    DisegnoService disegnoService;

    @Autowired
    public DisegnoController(DisegnoService disegnoService) {
        this.disegnoService = disegnoService;
    }


    @PostMapping("/creoDisegno/request")
    public ResponseEntity<Object> addDisegno(@RequestBody DisegnoAggiungiRequest request) {
        Disegno disegno = request.toDisegno(); //crea un disegno dalla richiesta
        disegnoService.aggiungiDisegno(disegno,request.utenteId); //aggiunge il disegno al database insieme all'id dell'utente
        return new ResponseEntity<>("Disegno inserito!", HttpStatus.OK); //ritorna il disegno aggiunto
        //STEP 1 : PRENDERE L'ID DELL'UTENTE DALLA RICHIESTA E CREARE IL DISEGNO DALLA RICHIESTA
        //STEP 2 : AGGIUNGERE IL DISEGNO AL DATABASE TRAMITE IL SERVIZIO, USANDO LE INFORMAZIONI PRESI SOPRA
        //STEP 3 : RITORNARE UNA RISPOSTA
    }



    @GetMapping("/prendereDisegno/{id}") //prendere un disegno
    public ResponseEntity<Object> getDisegno(@PathVariable("id")int id){
        Disegno disegno=  disegnoService.cercaDisegno(id);
        return new ResponseEntity<>(disegno, HttpStatus.OK);
    }

    @PutMapping("/modificaTesto/{id}") //modificare un disegno
    public ResponseEntity<Object> modificaDisegno(@PathVariable("id") int id, @RequestBody DisegnoModificaRequest request){
        disegnoService.modificaTitoloDisegno(id,request.titolo);
        return new ResponseEntity<>("Disegno modificato!", HttpStatus.OK);
    }

    @DeleteMapping("/eliminaDisegno/{id}") //eliminare un disegno
    public ResponseEntity<Object> eliminaDisegno(@PathVariable int id){
        disegnoService.rimuoviDisegno(id);
        return new ResponseEntity<>("Disegno eliminato!", HttpStatus.OK);
    }

}
