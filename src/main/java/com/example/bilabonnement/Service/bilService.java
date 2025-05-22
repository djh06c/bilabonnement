package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Repository.bilRepo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service // Marker klassen som en Service-komponent (Spring registrerer den automatisk)
public class bilService {

    private final bilRepo bilRepo;

    // Constructor injection af repository
    public bilService(bilRepo bilRepo) {
        this.bilRepo = bilRepo;
    }

    // Returnerer alle biler uden sortering
    public List<bilModel> hentAlleBiler() {
        return bilRepo.hentAlleBiler();
    }

    // Returnerer kun biler hvor tilgaengelig = true
    public List<bilModel> hentLedigeBiler() {
        return bilRepo.hentLedigeBiler();
    }

    // Sorterer biler så ledige står først
    public List<bilModel> hentBilerSorteretEfterLedighed() {
        List<bilModel> biler = bilRepo.hentAlleBiler();
        biler.sort((b1, b2) -> Boolean.compare(!b1.isTilgaengelig(), !b2.isTilgaengelig()));
        return biler;
    }

    // Sorterer biler efter stålpris (lav til høj)
    public List<bilModel> hentBilerSorteretEfterPris() {
        List<bilModel> biler = bilRepo.hentAlleBiler();
        biler.sort(Comparator.comparingDouble(bilModel::getStaalpris));
        return biler;
    }

    // Sorterer biler alfabetisk efter mærke (case-insensitivt)
    public List<bilModel> hentBilerSorteretEfterMaerke() {
        List<bilModel> biler = bilRepo.hentAlleBiler();
        biler.sort(Comparator.comparing(bilModel::getMaerke, String.CASE_INSENSITIVE_ORDER));
        return biler;
    }

    // Sorterer biler alfabetisk efter udstyrsniveau-navn (f.eks. STANDARD, PREMIUM)
    public List<bilModel> hentBilerSorteretEfterUdstyrsniveauNavn() {
        List<bilModel> biler = bilRepo.hentAlleBiler();
        biler.sort(Comparator.comparing(bilModel::getUdstyrsniveauNavn, String.CASE_INSENSITIVE_ORDER));
        return biler;
    }

    public List<bilModel> hentBilerSorteretEfterId() {
        return bilRepo.hentBilerSorteretEfter("bil_ID");
    }

    public List<bilModel> hentBilerSorteretEfterModel() {
        return bilRepo.hentBilerSorteretEfter("model");
    }

    public List<bilModel> hentBilerSorteretEfterRegnr() {
        return bilRepo.hentBilerSorteretEfter("regNr");
    }

    public List<bilModel> hentBilerSorteretEfterStelnummer() {
        return bilRepo.hentBilerSorteretEfter("stelNummer");
    }

    public List<bilModel> hentBilerSorteretEfterVognnummer() {
        return bilRepo.hentBilerSorteretEfter("vognNummer");
    }

    public List<bilModel> hentBilerSorteretEfterCO2() {
        return bilRepo.hentBilerSorteretEfter("co2");
    }

}
