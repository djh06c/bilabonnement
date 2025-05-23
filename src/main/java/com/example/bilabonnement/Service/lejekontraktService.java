package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Repository.lejekontraktRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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

}