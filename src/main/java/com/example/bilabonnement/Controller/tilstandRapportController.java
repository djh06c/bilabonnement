package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.tilstandRapportModel;
import com.example.bilabonnement.Service.tilstandRapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tilstandsrapporter")
public class tilstandRapportController {

    @Autowired
    private tilstandRapportService service;

    @GetMapping
    public List<tilstandRapportModel> hentAlleRapporter() {
        return service.hentAlleRapporter();
    }

    @GetMapping("/{id}")
    public tilstandRapportModel hentRapport(@PathVariable int id) {
        return service.hentRapportVedId(id);
    }

    @PostMapping
    public void opretRapport(@RequestBody tilstandRapportModel rapport) {
        service.opretRapport(rapport);
    }

    @PutMapping("/{id}")
    public void opdaterRapport(@PathVariable int id, @RequestBody tilstandRapportModel rapport) {
        rapport.setRapportID(id);
        service.opdaterRapport(rapport);
    }

    @DeleteMapping("/{id}")
    public void sletRapport(@PathVariable int id) {
        service.sletRapport(id);
    }
}