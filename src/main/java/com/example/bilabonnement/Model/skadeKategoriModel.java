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

    // Ekstra metode til visning, hvis du ønsker det
    public String getVurdering() {
        switch (kategoriID) {
            case 0: return "Ingen skade";
            case 1: return "Kosmetisk skade – kan udlejes";
            case 2: return "Skal repareres";
            case 3: return "Totalskadet – må ikke udlejes";
            default: return "Ukendt";
        }
    }
}
