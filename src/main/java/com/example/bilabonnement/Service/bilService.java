package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Model.udstyrsniveau;
import com.example.bilabonnement.Repository.bilRepo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class bilService {

    private final bilRepo bilRepo;

    // ✅ 1. Tilføj liste til at holde styr på senest oprettede biler
    private final LinkedList<bilModel> senesteBiler = new LinkedList<>();

    public bilService(bilRepo bilRepo) {
        this.bilRepo = bilRepo;
    }

    public List<bilModel> hentAlleBiler() {
        return bilRepo.hentAlleBiler();
    }

    public List<bilModel> hentBilerSorteretEfter(String kolonneNavn) {
        return bilRepo.hentBilerSorteretEfter(kolonneNavn);
    }

    // ✅ 2. Udvid opretBil til at holde styr på de nyeste
    public void opretBil(bilModel bil) {
        bilRepo.opretBil(bil);
        senesteBiler.addFirst(bil);
        if (senesteBiler.size() > 3) {
            senesteBiler.removeLast(); // behold kun de 3 nyeste
        }
    }

    // ✅ 3. Metode til at hente seneste biler til UI
    public List<bilModel> hentSenesteBiler(int antal) {
        List<bilModel> alleBiler = bilRepo.hentAlleBiler();
        alleBiler.sort((a, b) -> b.getBilId() - a.getBilId()); // sorter efter bilId faldende
        return alleBiler.stream().limit(antal).toList();
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
