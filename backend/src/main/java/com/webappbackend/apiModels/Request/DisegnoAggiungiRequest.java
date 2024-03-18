package com.webappbackend.apiModels.Request;

import com.webappbackend.modelli.Disegno;

import java.time.LocalDate;

public class DisegnoAggiungiRequest {
    public String nome;
    public String immagine;
    public int utenteId;


    public Disegno toDisegno() {
        Disegno disegno = new Disegno();
        disegno.setNome(nome);
        disegno.immagine = immagine;
        disegno.data = LocalDate.now();
        return disegno;
    }
}
