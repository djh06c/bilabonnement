package com.example.bilabonnement.Model;

public class loginModel {

    private int user_ID;
    private String brugernavn;
    private String adgangskodeHash;

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getAdgangskodeHash() {
        return adgangskodeHash;
    }

    public void setAdgangskodeHash(String adgangskodeHash) {
        this.adgangskodeHash = adgangskodeHash;
    }
}
