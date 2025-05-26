package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Repository.lejekontraktRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class lejekontraktService {

    private final lejekontraktRepo repo;

    public lejekontraktService(lejekontraktRepo repo) {
        this.repo = repo;
    }

    public List<lejekontraktModel> findAll() {
        return repo.findAll();
    }

    public lejekontraktModel findById(int id) {
        return repo.findById(id);
    }

    public void save(lejekontraktModel lejekontrakt) {
        repo.save(lejekontrakt);
    }

    public void update(lejekontraktModel lejekontrakt) {
        repo.save(lejekontrakt);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    /* --------------- Metode til at se om dags dato ligger før slutdato på kontrakt --------------- */
    /* --------------- Ergo om lejekontrakten er aktiv --------------- */
    public List<lejekontraktModel> findAktiveKontrakter(){
        LocalDate idag = LocalDate.now(); //opretter en instans af LocalDate.now()
        return findAll().stream() //Metode til at kigge igennem listen af alle lejekontrakter, stream er...
                // - en sekventiel databehandler
                .filter(lejekontrakt->!idag.isAfter(lejekontrakt.getSlutDato()))
                // For hver kontrakt der bliver streamet igenne, bliver den udsat for et filter...
                // - som tjekker at det ikke er sandt at "idag" er efter slutdatoen på den...
                // - aktuelle kontrakt i streamen.
                .collect(Collectors.toList()); //Til sidst, tilføjer den alle kontrakter som...
                // - er kommet igennem filteret, til en liste.
    }

    /* --------------- Metode til at se om dags dato er efter lejekontrakt slutdato --------------- */
    public List<lejekontraktModel> findUdløbneKontrakter(){
        LocalDate idag = LocalDate.now();
        return findAll().stream()
                .filter(lejekontrakt -> lejekontrakt.getSlutDato().isBefore(idag))
                .collect(Collectors.toList());
    }

}