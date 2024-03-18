package com.webappbackend.example;

import com.webappbackend.modelli.Disegno;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.DisegnoService;
import com.webappbackend.servizi.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitClassExample {
/*
  questa classe serve per inizializzare il database con alcuni dati di esempio
  così da non inserirli a mano ogni volta che si avvia l'applicazione
 */
    @Autowired
    InitClassExample(UtenteService utenteService, DisegnoService disegnoService){
        var utente1 = new Utente("utente1", "password1","mail");
        var utente2 = new Utente("utente2", "password2","mail");
        utente1 = utenteService.addUtente(utente1);
        utente2 = utenteService.addUtente(utente2);

        var disegno1 = new Disegno();
        disegno1.nome = "disegno1";
        disegno1.data = LocalDate.now();
        disegno1.immagine = "immagine1";
        disegnoService.aggiungiDisegno(disegno1, utente1.id);


        var disegno2 = new Disegno();
        disegno2.nome = "disegno2";
        disegno2.data = LocalDate.now();
        disegno2.immagine = "immagine2";
        disegnoService.aggiungiDisegno(disegno2, utente2.id);

        var disegno3 = new Disegno();
        disegno3.nome = "disegno3";
        disegno3.data = LocalDate.now();
        disegno3.immagine = "immagine3";
        disegnoService.aggiungiDisegno(disegno3, utente1.id);


    }


}
