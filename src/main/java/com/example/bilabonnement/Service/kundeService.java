package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Repository.kundeRepo;
import org.springframework.stereotype.Service;

import java.util.List;


// Denne klasse er service-laget for kunder og fungerer som bindeled mellem controller og repository
// Service-laget indeholder forretningslogik og sikrer, at controlleren ikke behøver at bekymre sig om databasen direkte
@Service
public class kundeService {

    // Her laver vi en attribut til kundeRepo, som vi bruger til at kalde metoderne, der arbejder direkte med databasen
    private final kundeRepo kundeRepo;

    // Parametiseret constructor – Spring sørger for at "indsprøjte" kundeRepo, så vi kan bruge det her
    // Her bliver alle metoder genbrugt fra repo som har CRUD-operationer
    public kundeService(kundeRepo kundeRepo) {
        this.kundeRepo = kundeRepo;
    }

    public List<kundeModel> hentAlleKunder() {
        return kundeRepo.hentAlleKunder();
    }

    public kundeModel findById(int id) {
        return kundeRepo.findById(id);
    }

    public void opretKunde(kundeModel kunde) {
        kundeRepo.opretKunde(kunde);
    }

    public void opdaterKunde(kundeModel kunde) {
        kundeRepo.opdaterKunde(kunde);
    }

    public void sletKunde(int id) {
        kundeRepo.sletKunde(id);
    }
}