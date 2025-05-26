package com.example.bilabonnement.Model;

public class tilstandRapportModel {

    private int rapportID;
    private int kontraktID;
    private int bilID;
    private int kategoriID;
    private String beskrivelse;

    public tilstandRapportModel() {
    }

    public int getRapportID() {
        return rapportID;
    }

    public void setRapportID(int rapportID) {
        this.rapportID = rapportID;
    }

    public int getKontraktID() {
        return kontraktID;
    }

    public void setKontraktID(int kontraktID) {
        this.kontraktID = kontraktID;
    }

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}
