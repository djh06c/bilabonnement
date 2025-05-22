package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class kundeModel{
private int kundeID;
private String navn;
private String email;
private String adresse;
private String tlfNr;
private LocalDate foedselsdato;

public kundeModel() {}

public kundeModel(int kundeID, String navn, String email, String adresse, String tlfNr, LocalDate foedselsdato){
    this.kundeID = kundeID;
    this.navn = navn;
    this.email = email;
    this.adresse = adresse;
    this.tlfNr = tlfNr;
    this.foedselsdato = foedselsdato;
}

    public int getKundeID() {
        return kundeID;
    }

    public void setKundeID(int kundeID) {
        this.kundeID = kundeID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTlfNr() {
        return tlfNr;
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr = tlfNr;
    }

    public LocalDate getFoedselsdato() {
        return foedselsdato;
    }

    public void setFoedselsdato(LocalDate foedselsdato) {
        this.foedselsdato = foedselsdato;
    }

    public String getKontaktInfo() {
        return "Navn: " + navn + ", Telefon: " + tlfNr + ", Email: " + email;
    }
}