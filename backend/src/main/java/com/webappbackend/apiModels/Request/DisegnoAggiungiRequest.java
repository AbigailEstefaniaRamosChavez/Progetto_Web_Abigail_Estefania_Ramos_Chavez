package com.webappbackend.apiModels.Request;

import com.webappbackend.modelli.Disegno;

import java.time.LocalDate;

public class DisegnoAggiungiRequest {
    public String nome;
    public String descrizione;




    public Disegno toDisegno() {
        Disegno disegno = new Disegno();
        disegno.setNome(nome);
        disegno.descrizione = descrizione;
        disegno.data = LocalDate.now();
        return disegno;
    }
}
