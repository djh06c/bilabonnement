package com.example.bilabonnement.Model;

import jakarta.validation.constraints.*;

public class bilModel {

    private int bilId;

    @NotBlank(message = "Vognnummer må ikke være tomt")
    private String vognNummer;

    @NotBlank(message = "Stelnummer må ikke være tomt")
    private String stelNummer;

    @NotBlank(message = "Registreringsnummer må ikke være tomt")
    private String regNr;

    @NotBlank(message = "Model må ikke være tom")
    private String model;

    @NotBlank(message = "Mærke må ikke være tomt")
    private String maerke;

    @NotNull(message = "CO2 skal udfyldes")
    @DecimalMin(value = "0.0", inclusive = true, message = "CO2 skal være ≥ 0")
    @Digits(integer = 6, fraction = 2, message = "CO2 skal være et gyldigt decimaltal (f.eks. 95.0)")
    private Double co2;

    private boolean tilgaengelig;

    @NotNull(message = "Stålpris skal udfyldes")
    @DecimalMin(value = "0.0", inclusive = true, message = "Stålpris skal være ≥ 0")
    @Digits(integer = 9, fraction = 2, message = "Stålpris skal være et gyldigt decimaltal")
    private Double staalpris;

    @NotNull(message = "Månedspris skal udfyldes")
    @DecimalMin(value = "0.0", inclusive = true, message = "Månedspris skal være ≥ 0")
    @Digits(integer = 6, fraction = 2, message = "Månedspris skal være et gyldigt decimaltal")
    private Double maanedspris;

    @NotNull(message = "Udstyrsniveau skal vælges")
    private Udstyrsniveau udstyrsniveau;

    // --- Getters og setters ---

    public int getBilId() { return bilId; }

    public void setBilId(int bilId) { this.bilId = bilId; }

    public String getVognNummer() { return vognNummer; }

    public void setVognNummer(String vognNummer) { this.vognNummer = vognNummer; }

    public String getStelNummer() { return stelNummer; }

    public void setStelNummer(String stelNummer) { this.stelNummer = stelNummer; }

    public String getRegNr() { return regNr; }

    public void setRegNr(String regNr) { this.regNr = regNr; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getMaerke() { return maerke; }

    public void setMaerke(String maerke) { this.maerke = maerke; }

    public Double getCo2() { return co2; }

    public void setCo2(Double co2) { this.co2 = co2; }

    public boolean isTilgaengelig() { return tilgaengelig; }

    public void setTilgaengelig(boolean tilgaengelig) { this.tilgaengelig = tilgaengelig; }

    public Double getStaalpris() { return staalpris; }

    public void setStaalpris(Double staalpris) { this.staalpris = staalpris; }

    public Double getMaanedspris() { return maanedspris; }

    public void setMaanedspris(Double maanedspris) { this.maanedspris = maanedspris; }

    public Udstyrsniveau getUdstyrsniveau() { return udstyrsniveau; }

    public void setUdstyrsniveau(Udstyrsniveau udstyrsniveau) { this.udstyrsniveau = udstyrsniveau; }

    // --- toString ---
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
                ", maanedspris=" + maanedspris +
                ", udstyrsniveau=" + udstyrsniveau +
                '}';
    }

    // --- equals og hashCode baseret på bilId ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof bilModel bil)) return false;
        return bilId == bil.bilId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(bilId);
    }
}
