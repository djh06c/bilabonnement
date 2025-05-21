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

    public List<skadeKategoriModel> hentAlleKategorier() {
        return repo.hentAlleKategorier();
    }

    public skadeKategoriModel hentKategoriVedId(int id) {
        return repo.hentKategoriVedId(id);
    }

    public void opretKategori(skadeKategoriModel kategori) {
        repo.opretKategori(kategori);
    }

    public void opdaterKategori(skadeKategoriModel kategori) {
        repo.opdaterKategori(kategori);
    }

    public void sletKategori(int id) {
        repo.sletKategori(id);
    }
}