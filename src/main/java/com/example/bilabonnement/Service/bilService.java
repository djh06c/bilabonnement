package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Model.udstyrsniveau;
import com.example.bilabonnement.Repository.bilRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bilService {

    private final bilRepo bilRepo;

    public bilService(bilRepo bilRepo) {
        this.bilRepo = bilRepo;
    }

    public List<bilModel> hentAlleBiler() {
        return bilRepo.hentAlleBiler();
    }

    public List<bilModel> hentLedigeBiler() {
        return bilRepo.hentLedigeBiler();
    }

    public List<bilModel> hentBilerSorteretEfter(String kolonneNavn) {
        return bilRepo.hentBilerSorteretEfter(kolonneNavn);
    }

    public void opretBil(bilModel bil) {
        bilRepo.opretBil(bil);
    }

    public bilModel findBilById(int id) {
        return bilRepo.findById(id);
    }

    public void opdaterBil(bilModel bil) {
        bilRepo.opdaterBil(bil);
    }

    public void sletBil(int id) {
        bilRepo.sletBil(id);
    }

    public List<udstyrsniveau> hentAlleUdstyrsniveauer() {
        return bilRepo.hentAlleUdstyrsniveauer();
    }
}
