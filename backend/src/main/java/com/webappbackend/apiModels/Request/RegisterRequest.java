package com.webappbackend.apiModels.Request;

import com.webappbackend.modelli.Utente;

public class RegisterRequest {
    public String username;
    public String password;
    public String email;

    public Utente toUtente() {
        return new Utente(username, password, email);
    }
}
