package com.example.bilabonnement.Model;

public class skadeKategoriModel {

    private int kategoriID;
    private String navn;
    private String beskrivelse;

    public skadeKategoriModel() {}

    public skadeKategoriModel(int kategoriID, String navn, String beskrivelse) {
        this.kategoriID = kategoriID;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}
