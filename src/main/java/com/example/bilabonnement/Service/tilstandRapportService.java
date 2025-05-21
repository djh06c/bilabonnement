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

    public List<tilstandRapportModel> hentAlleRapporter() {
        return repo.hentAlleRapporter();
    }

    public tilstandRapportModel hentRapportVedId(int id) {
        return repo.hentRapportVedId(id);
    }

    public void opretRapport(tilstandRapportModel rapport) {
        repo.opretRapport(rapport);
    }

    public void opdaterRapport(tilstandRapportModel rapport) {
        repo.opdaterRapport(rapport);
    }

    public void sletRapport(int id) {
        repo.sletRapport(id);
    }
}