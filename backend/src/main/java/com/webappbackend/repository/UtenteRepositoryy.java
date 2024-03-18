package com.webappbackend.repository;

import com.webappbackend.modelli.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepositoryy extends JpaRepository<Utente,Integer> {

}
