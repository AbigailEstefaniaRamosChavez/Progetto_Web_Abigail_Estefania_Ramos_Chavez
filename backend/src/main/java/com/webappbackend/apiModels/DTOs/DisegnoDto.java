package com.webappbackend.apiModels.DTOs;

import com.webappbackend.modelli.Disegno;

public class DisegnoDto {
    public long id;
    public String nome;
    public String immagine;
    public String data;
    public UtenteDto utente;
    public String descrizione;
    public String path;

    public DisegnoDto(Disegno disegno) {
        this.id = disegno.id;
        this.nome = disegno.nome;
        this.immagine = disegno.immagine;
        this.data = disegno.data.toString();
        this.utente = new UtenteDto(disegno.utente);
        this.descrizione = disegno.descrizione;
        this.path = disegno.path;
    }
}
