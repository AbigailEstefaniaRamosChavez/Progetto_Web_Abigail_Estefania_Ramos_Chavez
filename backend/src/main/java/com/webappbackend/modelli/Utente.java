package com.webappbackend.modelli;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class    Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String username;
    @JsonIgnore
    public String password;
    public String email;

    public String salt;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
