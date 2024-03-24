package com.webappbackend.controllers.utils;

import com.webappbackend.modelli.Utente;

import java.util.Objects;

public class DaiUtenteUtil {

    public static Utente DaiUtente(Object principal){
        if(principal == null)
            return null;
        if(principal instanceof Utente)
            return (Utente) principal;
        return null;
    }

    public static int DaiIdUtente(Object principal){
        Utente utente = DaiUtente(principal);
        if(utente == null)
            return -1;
        return utente.getId();
    }
}
