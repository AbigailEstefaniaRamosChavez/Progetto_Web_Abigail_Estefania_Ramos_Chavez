package com.webappbackend.repository;

import com.webappbackend.modelli.Disegno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisegnoRepository  extends JpaRepository<Disegno, Long> {
    //VA BENE CHE HO MESSO DISEGNO E LONG?
}
