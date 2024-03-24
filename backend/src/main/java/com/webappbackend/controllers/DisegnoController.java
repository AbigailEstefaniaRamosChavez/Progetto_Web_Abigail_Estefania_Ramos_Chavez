package com.webappbackend.controllers;

import com.webappbackend.apiModels.Request.DisegnoAggiungiRequest;
import com.webappbackend.apiModels.Request.DisegnoModificaRequest;
import com.webappbackend.apiModels.Responses.ImageResponse;
import com.webappbackend.controllers.utils.DaiUtenteUtil;
import com.webappbackend.modelli.Disegno;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.DisegnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v0/disegno")
public class DisegnoController {
    DisegnoService disegnoService;

    @Autowired
    public DisegnoController(DisegnoService disegnoService) {
        this.disegnoService = disegnoService;
    }


    @PostMapping(value = "/creoDisegno/request" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addDisegno(@RequestParam("titolo") String titolo, @RequestParam("descrizione") String descrizione, @RequestParam("file") MultipartFile file ) throws IOException {
        Utente utente = DaiUtenteUtil.DaiUtente(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        Disegno disegno = new Disegno();
        disegno.setNome(titolo);
        disegno.descrizione = descrizione;
        disegno.data = LocalDate.now();
        disegno = disegnoService.aggiungiDisegno(disegno, utente.id); //aggiunge il disegno al database insieme all'id dell'utente
        Path path = Paths.get("src/main/resources/static/assets/img/" + disegno.id + ".jpeg"); //crea un percorso per salvare l'immagine
        File fileCreato = new File(path.toString()); //crea un file
        fileCreato.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileCreato)) {
            fileOutputStream.write(file.getBytes()); //scrive l'immagine nel file
        }

        return new ResponseEntity<>("Disegno inserito!", HttpStatus.OK); //ritorna il disegno aggiunto
    }



    @GetMapping("/prendereDisegno/{id}") //prendere un disegno
    public ResponseEntity<Object> getDisegno(@PathVariable("id")int id){
        Disegno disegno=  disegnoService.cercaDisegno(id);
        return new ResponseEntity<>(disegno, HttpStatus.OK);
    }

    @PutMapping("/modificaTesto/{id}") //modificare un disegno
    public ResponseEntity<Object> modificaDisegno(@PathVariable("id") int id, @RequestBody DisegnoModificaRequest request){
        disegnoService.modificaTitoloDisegno(id,request.titolo);
        return new ResponseEntity<>("Disegno modificato!", HttpStatus.OK);
    }

    @DeleteMapping("/eliminaDisegno/{id}") //eliminare un disegno
    public ResponseEntity<Object> eliminaDisegno(@PathVariable int id){
        disegnoService.rimuoviDisegno(id);
        return new ResponseEntity<>("Disegno eliminato!", HttpStatus.OK);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<ImageResponse> getImg(@PathVariable("id") int id) throws IOException {
        Path path = Paths.get("src/main/resources/static/assets/img/" + id + ".jpeg");
        File file = new File(path.toString());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ImageResponse(resource.getContentAsByteArray()));
    }

}
