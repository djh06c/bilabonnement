package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Repository.kundeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class kundeService {

    private final kundeRepo kundeRepo;

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