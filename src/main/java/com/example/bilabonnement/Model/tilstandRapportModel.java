package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class tilstandRapportModel {
    private int rapportID;
    private int bilID;
    private int kontraktID;
    private int kategoriID;
    private String beskrivelse;

    public tilstandRapportModel() {}

    public tilstandRapportModel(int rapportID, int bilID, int kontraktID, LocalDate dato, int kategoriID, String beskrivelse) {
        this.rapportID = rapportID;
        this.bilID = bilID;
        this.kontraktID = kontraktID;
        this.kategoriID = kategoriID;
        this.beskrivelse = beskrivelse;
    }

    public int getRapportID() {
        return rapportID;
    }

    public void setRapportID(int rapportID) {
        this.rapportID = rapportID;
    }

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

    public int getKontraktID() {
        return kontraktID;
    }

    public void setKontraktID(int kontraktID) {
        this.kontraktID = kontraktID;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getbeskrivelse() {
        return beskrivelse;
    }

    public void setbeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}