package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Service.kundeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kunder")
public class kundeController {

    private final kundeService kundeService;

    public kundeController(kundeService kundeService) {
        this.kundeService = kundeService;
    }


    @GetMapping
    public List<kundeModel> hentAlle() {
        return kundeService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<kundeModel> hentEn(@PathVariable int id) {
        kundeModel kunde = kundeService.findById(id);
        if (kunde != null) {
            return ResponseEntity.ok(kunde);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<String> opret(@RequestBody kundeModel kunde) {
        kundeService.save(kunde);
        return ResponseEntity.status(HttpStatus.CREATED).body("Kunde oprettet");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> opdater(@PathVariable int id, @RequestBody kundeModel kunde) {
        kunde.setKundeID(id);
        kundeService.update(kunde);
        return ResponseEntity.ok("Kunde opdateret");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> slet(@PathVariable int id) {
        kundeService.deleteById(id);
        return ResponseEntity.ok("Kunde slettet");
    }
}
