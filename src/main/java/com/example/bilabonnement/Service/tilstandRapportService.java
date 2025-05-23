package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.tilstandRapportModel;
import com.example.bilabonnement.Repository.tilstandRapportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tilstandRapportService {

    @Autowired
    private tilstandRapportRepo repo;

    // 🔹 Hent alle rapporter
    public List<tilstandRapportModel> hentAlleRapporter() {
        return repo.hentAlleRapporter();
    }

    // 🔹 Hent rapport ved ID (valgfri funktion)
    public tilstandRapportModel hentRapportVedId(int id) {
        return repo.hentRapportVedId(id);
    }

    // 🔹 Opret ny rapport
    public void opretRapport(tilstandRapportModel rapport) {
        repo.opretRapport(rapport);
    }

    // 🔹 Opdater rapport (valgfri)
    public void opdaterRapport(tilstandRapportModel rapport) {
        repo.opdaterRapport(rapport);
    }

    // 🔹 Slet rapport (valgfri)
    public void sletRapport(int id) {
        repo.sletRapport(id);
    }
}
