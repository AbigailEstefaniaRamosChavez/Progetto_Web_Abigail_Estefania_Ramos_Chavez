package com.webappbackend.controllers;

import com.webappbackend.apiModels.DTOs.DtoResponse;
import com.webappbackend.apiModels.Request.DisegnoModificaRequest;
import com.webappbackend.controllers.utils.DaiUtenteUtil;
import com.webappbackend.controllers.utils.FileHandler;
import com.webappbackend.modelli.Disegno;
import com.webappbackend.modelli.Utente;
import com.webappbackend.servizi.DisegnoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/v0/disegno")
public class DisegnoController {

    DisegnoService disegnoService;
    private FileHandler fh;


    public DisegnoController(DisegnoService disegnoService) {
        this.disegnoService = disegnoService;
        this.fh = new FileHandler();
    }


    @PostMapping(value = "/creoDisegno/request" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addDisegno(@RequestParam("titolo") String titolo, @RequestParam("descrizione") String descrizione, @RequestBody MultipartFile file ) throws IOException {
        Utente utente = DaiUtenteUtil.DaiUtente(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        Disegno disegno = new Disegno();
        disegno.setNome(titolo);
        disegno.descrizione = descrizione;
        disegno.data = LocalDate.now();
        disegno.path = this.fh.getName(file);
        disegno = disegnoService.aggiungiDisegno(disegno, utente.id); //aggiunge il disegno al database insieme all'id dell'utente
        return new ResponseEntity<>(new DtoResponse("Disegno inserito!"), HttpStatus.OK); //ritorna il disegno aggiunto
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
    public ResponseEntity<?> getImg(@PathVariable("id") int id) throws IOException {
        Disegno disegno = disegnoService.cercaDisegno(id);
        Resource file = this.fh.getFile(disegno.path);
		String mimeType = "";
	    try {
	        mimeType = Files.probeContentType(Paths.get(file.getURI()));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
	            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }



}
