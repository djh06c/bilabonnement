package com.example.bilabonnement.Model;

public class skadeKategoriModel {
    private int kategoriID;
    private String navn;
    private String beskrivelse;

    public skadeKategoriModel(){}

    public skadeKategoriModel(int kategoriID, String navn, String beskrivelse){
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
    public String getVurdering(){
        switch (kategoriID){
            case 0:
                return "Ingen skader";
            case 1:
                return "Kosmetisk skade - Stadig mulighed for udlejning";
            case 2:
                return "Funktionsfejl - skal repareres før udlejning";
            case 3:
                return "Totalskadet - bilen må ikke udlejes";
            default:
                return "Ikke vurderet";
        }
        }
    }

