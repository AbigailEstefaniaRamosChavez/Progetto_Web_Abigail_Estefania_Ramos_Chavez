package com.webappbackend.modelli;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Disegno {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    @ManyToOne(fetch = FetchType.EAGER)
    public Utente utente;

    public String nome;

    public String immagine;

    public String descrizione;

    public LocalDate data;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }





}
