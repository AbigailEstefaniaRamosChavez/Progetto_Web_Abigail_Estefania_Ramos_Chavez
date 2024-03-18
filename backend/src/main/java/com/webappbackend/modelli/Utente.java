package com.webappbackend.modelli;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String username;
    @JsonIgnore
    public String password;
    public String email;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utente")
    public List<Disegno> disegni;

    public List<Disegno> getDisegni() {
        return disegni;
    }

    public void setDisegni(List<Disegno> disegni) {
        this.disegni = disegni;
    }

    public Utente(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Utente() {

    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
