package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Repository.bilRepo;
import org.springframework.stereotype.Service;
import com.example.bilabonnement.Model.Udstyrsniveau;
import java.util.LinkedList;
import java.util.List;

@Service
public class bilService {

    private final bilRepo bilRepo;

    // ✅ Holder kun biler oprettet via hjemmesiden
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

    // ✅ Tilføjelse via hjemmeside – gemmes i senesteBiler
    public void opretBil(bilModel bil) {
        bilRepo.opretBil(bil);
        bilModel nyeste = bilRepo.findNyesteBil(); // Hent den nyeste med højest bilId
        senesteBiler.addFirst(nyeste);
        if (senesteBiler.size() > 3) {
            senesteBiler.removeLast(); // Behold kun de 3 nyeste
        }
    }

    // ✅ Returnér kun dem vi har husket
    public List<bilModel> hentSenesteBiler(int antal) {
        return senesteBiler.stream().limit(antal).toList();
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

    public List<Udstyrsniveau> hentAlleUdstyrsniveauer() {
        return bilRepo.hentAlleUdstyrsniveauer();
    }
}