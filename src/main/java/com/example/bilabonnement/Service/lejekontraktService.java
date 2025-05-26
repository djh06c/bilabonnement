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
    public List<lejekontraktModel> findAktiveKontrakter(){
        LocalDate idag = LocalDate.now();
        return findAll().stream()
                .filter(lejekontrakt->!idag.isAfter(lejekontrakt.getSlutDato()))
                .collect(Collectors.toList());
    }

    /* --------------- Metode til at se om dags dato er efter lejekontrakt slutdato --------------- */
    public List<lejekontraktModel> findUdløbneKontrakter(){
        LocalDate idag = LocalDate.now();
        return findAll().stream()
                .filter(lejekontrakt -> lejekontrakt.getSlutDato().isBefore(idag))
                .collect(Collectors.toList());
    }

}