package com.webappbackend.servizi;

import com.webappbackend.modelli.Disegno;
import com.webappbackend.repository.DisegnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisegnoService {
    private DisegnoRepository disegnoRepository;
    private UtenteService utenteService;


    @Autowired
    public DisegnoService(DisegnoRepository disegnoRepository, UtenteService utenteService) {
      this.disegnoRepository = disegnoRepository;
      this.utenteService = utenteService;
    }


    /**
     * ha il poroblema di essere in relazione con utente, quindi quando aggiungo
     * un disegno devo aggingere al disegno anche l'utente associato
     */

    /**
     * STEP 1 PRESENZA DELL'UTENTE NEL DATABASE
     * STEP 2 COLLEGO L'UTENTE(SE PRESENTE) AL DISEGNO
     * STEP 3 SALVARE IL DISEGNO
     * STEP 4 RESTITUIRE IL DISEGNO AGGIUNTO
     * aggiunge un disegno al database di disegni, coolegandolo ad un utente
     * @param disegno
     * @param idUtente
     */
    public Disegno aggiungiDisegno(Disegno disegno, int idUtente){
        var utente = utenteService.getUtente(idUtente);
        if(utente != null){
            disegno.utente = utente;
            //utente.disegni.add(disegno); //inserisco il disegno nella lista di disegni dell'utente
            //disegno.utente=utenteService.modificaUtente(utente); //utente viene modificato inserendo il disegno
            return disegnoRepository.save(disegno); //disegno viene salvato
        }else{
            throw new IllegalArgumentException("utente non presente");
        }
        }


    /**
     * STEP 1 PRESENZA DEL DISEGNO NEL DATABASE
     * STEP 2 SALVARE IL NUOVO DISEGNO (GUARDA LA MODIFICA IN UTENTE SERVICE)
     * STEP 3 RESTITUIRE IL DISEGNO MODIFICATO
     *
     * modifica un disegno nel database di disegni
     * @param idDisegno
     */
    public Disegno modificaTitoloDisegno(long idDisegno, String titolo){
        var disegnodb = disegnoRepository.findById(idDisegno).orElse(null);
        if(disegnodb!=null){
            disegnodb.setNome(titolo);
            return disegnoRepository.save(disegnodb);
        }else{
            throw new IllegalArgumentException("disegno non presente");
        }
    }



    //RIMUOVI Disegno
    public void rimuoviDisegno(long idDisegno){
        disegnoRepository.deleteById(idDisegno);
    }

    //CERCA Disegno
    public Disegno cercaDisegno(long idDisegno){
        return disegnoRepository.findById(idDisegno).orElse(null);
    }
}
