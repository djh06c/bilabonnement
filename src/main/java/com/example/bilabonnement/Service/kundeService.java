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


    public List<kundeModel> findAll() {
        return kundeRepo.findAll();
    }


    public kundeModel findById(int id) {
        return kundeRepo.findById(id);
    }


    public void save(kundeModel kunde) {
        kundeRepo.save(kunde);
    }


    public void update(kundeModel kunde) {
        kundeRepo.update(kunde);
    }


    public void deleteById(int id) {
        kundeRepo.deleteById(id);
    }
}
