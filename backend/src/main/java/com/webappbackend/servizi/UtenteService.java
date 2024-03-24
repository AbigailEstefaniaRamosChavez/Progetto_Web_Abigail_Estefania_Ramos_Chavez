package com.webappbackend.servizi;

import com.webappbackend.modelli.Utente;
import com.webappbackend.repository.UtenteRepositoryy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    UtenteRepositoryy utenteRepository;
    @Autowired
    public UtenteService(UtenteRepositoryy utenteRepository) {
        this.utenteRepository = utenteRepository;
    }



    //metodo che cerca un utente
    public Utente getUtente(int id) {
        return utenteRepository//UtenteRepository
                .findById(id)//Optional<Utente>
                .orElse(null);//Utente
    }

    //metodo che cerca un utente
    public Utente getUtente(String username) {
        return utenteRepository.findAll().stream()
                .filter(utente -> utente.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }


     //metodo che aggiunge un utente
    public Utente addUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    //metodo che rimuove un utente
    public void removeUtente(int id) {
        utenteRepository.deleteById(id);
    }

    //metodo che modifica un utente
    public Utente modificaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }



}
