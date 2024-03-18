package com.webappbackend.repository;

import com.webappbackend.modelli.Utente;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UtenteRepository {
    private Set<Utente> utenti = new HashSet<>();
    //aggiungi, rimuovi, cambia, cerca

   public Utente getUtente(int id){
        for (Utente utente : utenti) {
            if (utente.getId() == id) {
                return utente;
            }
        }
        return null;
    }

    public Utente getUtente(String username){
       for(Utente utente: utenti){
           if(utente.getUsername().equals(username)){
               return utente;
           }
       }
        return null;
    }
    public void addUtente(Utente utente) {utenti.add(utente);}

    public void removeUtente (int id) {
        for (Utente utente : utenti) {
            if (utente.getId() == id) {
                utenti.remove(utente);
            }
        }
    }

    public void modificaUtente (Utente utente) {
        for (Utente user : utenti) {
            if (user.getId() == utente.getId()) {
                utenti.remove(user);
              //  utente = new Utente(1, "username", "password", "email");
                utenti.add(utente);
            }
        }
    }

    public boolean cercaUtente (int id) {
        for (Utente utente: utenti){
            if (utente.getId()==id){
                return true;
            }
        }
        return false;
    }

}
