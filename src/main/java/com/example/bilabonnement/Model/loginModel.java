package com.example.bilabonnement.Model;

public class loginModel {

    private String brugerNavn;
    private String adgangskodeHash;

    public loginModel(){}

    public loginModel(String brugerNavn, String adgangskodeHash) {
        this.brugerNavn = brugerNavn;
        this.adgangskodeHash = adgangskodeHash;
    }

    public String getBrugerNavn() {
        return brugerNavn;
    }
    public String getAdgangskodeHash() {
        return adgangskodeHash;
    }

    public void setBrugerNavn(String brugerNavn) {
        this.brugerNavn = brugerNavn;
    }

    public void setAdgangskodeHash(String adgangskodeHash) {
        this.adgangskodeHash = adgangskodeHash;
    }

}
