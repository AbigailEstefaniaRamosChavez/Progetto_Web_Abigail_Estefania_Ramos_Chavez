package com.webappbackend;

import com.webappbackend.modelli.Disegno;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.DisegnoService;
import com.webappbackend.servizi.UtenteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebAppBackendApplicationTests {
    @Autowired
    UtenteService utenteService;
    @Autowired
    DisegnoService disegnoService;

    @Test
    public void addUtenteTest() {
        var utente = utenteService.addUtente(new Utente("username", "password", "email"));
        utente = utenteService.getUtente(utente.id);
        assertEquals(utente.getUsername(), "username");
        assertEquals(utente.getPassword(), "password");
        assertEquals(utente.getEmail(), "email");
    }

    @Test
    public void removeUtenteTest(){
        Utente utente = new Utente("username", "password", "email");
        utenteService.addUtente(utente);
        utente = utenteService.getUtente(1);
        assertEquals(utente.getId(),1);
        utenteService.removeUtente(1);
        assertNull(utenteService.getUtente(1));
    }

    @Test
    public void aggiungiDisegno(){
        //preliminare, mi serve un utente per mettere un disegno
        Utente utente = new Utente("username", "password", "email");
        utente = utenteService.addUtente(utente);
        //adesso vedo se il disegno viene aggiunto correttamente
        Disegno disegno = new Disegno();
        disegno = disegnoService.aggiungiDisegno(disegno, utente.id);

        disegno = disegnoService.cercaDisegno(disegno.id); //TOGLI IL COMMENTO QUANDO AVRAI AGGIUNTO IL METODO

        assertNotEquals(null, disegno.utente);
        assertEquals(utente.id, disegno.utente.id);
        assertEquals(utenteService.getUtente(utente.id).disegni.get(0).id, disegno.id);//TOGLI IL COMMENTO SE TI FUNZIONA IL METODO SOPRA
    }









}
