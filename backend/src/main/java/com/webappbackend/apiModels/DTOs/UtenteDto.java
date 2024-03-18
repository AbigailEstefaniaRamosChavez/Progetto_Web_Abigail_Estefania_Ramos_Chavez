package com.webappbackend.apiModels.DTOs;

import com.webappbackend.modelli.Utente;

public class UtenteDto {

    public int id;

    public String username;

    public String email;

    public UtenteDto(Utente utente) {
        this.id = utente.getId();
        this.username = utente.getUsername();
        this.email = utente.getEmail();
    }
}
