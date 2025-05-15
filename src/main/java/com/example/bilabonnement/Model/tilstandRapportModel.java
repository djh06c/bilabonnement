package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class tilstandRapportModel {
    private int rapportID;
    private int bilID;
    private int kontraktID;
    private LocalDate dato;
    private int kategoriID;
    private String skadeBeskrivelse;

    public tilstandRapportModel() {}

    public tilstandRapportModel(int rapportID, int bilID, int kontraktID, LocalDate dato, int kategoriID, String skadeBeskrivelse) {
        this.rapportID = rapportID;
        this.bilID = bilID;
        this.kontraktID = kontraktID;
        this.dato = dato;
        this.kategoriID = kategoriID;
        this.skadeBeskrivelse = skadeBeskrivelse;
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

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getSkadeBeskrivelse() {
        return skadeBeskrivelse;
    }

    public void setSkadeBeskrivelse(String skadeBeskrivelse) {
        this.skadeBeskrivelse = skadeBeskrivelse;
    }
}