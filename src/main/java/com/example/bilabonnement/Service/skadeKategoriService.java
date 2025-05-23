package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Repository.skadeKategoriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class skadeKategoriService {

    @Autowired
    private skadeKategoriRepo repo;

    // 🔹 Hent alle skadekategorier
    public List<skadeKategoriModel> hentAlleKategorier() {
        return repo.hentAlleKategorier();
    }

    // 🔹 Hent én kategori
    public skadeKategoriModel hentKategoriVedId(int id) {
        return repo.hentKategoriVedId(id);
    }

    // 🔹 Opret ny kategori
    public void opretKategori(skadeKategoriModel kategori) {
        repo.opretKategori(kategori);
    }

    // 🔹 Opdater eksisterende kategori
    public void opdaterKategori(skadeKategoriModel kategori) {
        repo.opdaterKategori(kategori);
    }

    // 🔹 Slet kategori
    public void sletKategori(int id) {
        repo.sletKategori(id);
    }
}
