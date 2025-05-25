package com.example.bilabonnement.Model;

public class bilModel {
    private int bilId;
    private String vognNummer;
    private String stelNummer;
    private String regNr;
    private String model;
    private String maerke;
    private double co2;
    private boolean tilgaengelig;
    private double staalpris;
    private int udstyrsniveauId;
    private double maanedspris;

    // --- Getters og setters ---

    public int getBilId() {
        return bilId;
    }

    public void setBilId(int bilId) {
        this.bilId = bilId;
    }

    public String getVognNummer() {
        return vognNummer;
    }

    public void setVognNummer(String vognNummer) {
        this.vognNummer = vognNummer;
    }

    public String getStelNummer() {
        return stelNummer;
    }

    public void setStelNummer(String stelNummer) {
        this.stelNummer = stelNummer;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public boolean isTilgaengelig() {
        return tilgaengelig;
    }

    public void setTilgaengelig(boolean tilgaengelig) {
        this.tilgaengelig = tilgaengelig;
    }

    public double getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(double staalpris) {
        this.staalpris = staalpris;
    }

    public int getUdstyrsniveauId() {
        return udstyrsniveauId;
    }

    public void setUdstyrsniveauId(int udstyrsniveauId) {
        this.udstyrsniveauId = udstyrsniveauId;
    }

    public double getMaanedspris() {return maanedspris;}

    public void setMaanedspris(double maanedspris) {this.maanedspris = maanedspris;}

    // --- toString() til debug ---
    @Override
    public String toString() {
        return "bilModel{" +
                "bilId=" + bilId +
                ", vognNummer='" + vognNummer + '\'' +
                ", stelNummer='" + stelNummer + '\'' +
                ", regNr='" + regNr + '\'' +
                ", model='" + model + '\'' +
                ", maerke='" + maerke + '\'' +
                ", co2=" + co2 +
                ", tilgaengelig=" + tilgaengelig +
                ", staalpris=" + staalpris +
                ", udstyrsniveauId=" + udstyrsniveauId +
                '}';
    }

    private String udstyrsniveauNavn;

    public String getUdstyrsniveauNavn() {
        return udstyrsniveauNavn;
    }

    public void setUdstyrsniveauNavn(String udstyrsniveauNavn) {
        this.udstyrsniveauNavn = udstyrsniveauNavn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        bilModel bil = (bilModel) o;

        return bilId == bil.bilId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(bilId);
    }

}
