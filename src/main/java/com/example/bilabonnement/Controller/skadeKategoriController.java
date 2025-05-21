package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Service.skadeKategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skadekategorier")
public class skadeKategoriController {

    @Autowired
    private skadeKategoriService service;

    @GetMapping
    public List<skadeKategoriModel> hentAlle() {
        return service.hentAlleKategorier();
    }

    @GetMapping("/{id}")
    public skadeKategoriModel hentKategori(@PathVariable int id) {
        return service.hentKategoriVedId(id);
    }

    @PostMapping
    public void opretKategori(@RequestBody skadeKategoriModel kategori) {
        service.opretKategori(kategori);
    }

    @PutMapping("/{id}")
    public void opdaterKategori(@PathVariable int id, @RequestBody skadeKategoriModel kategori) {
        kategori.setKategoriID(id);
        service.opdaterKategori(kategori);
    }

    @DeleteMapping("/{id}")
    public void sletKategori(@PathVariable int id) {
        service.sletKategori(id);
    }
}