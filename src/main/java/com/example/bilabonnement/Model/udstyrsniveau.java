package com.example.bilabonnement.Model;

public class udstyrsniveau {
    private int udstyrsniveauId;
    private String navn;

    // --- Getters og Setters ---

    public int getUdstyrsniveauId() {
        return udstyrsniveauId;
    }

    public void setUdstyrsniveauId(int udstyrsniveauId) {
        this.udstyrsniveauId = udstyrsniveauId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    // --- Optional: toString ---
    @Override
    public String toString() {
        return "udstyrsniveau{" +
                "udstyrsniveauId=" + udstyrsniveauId +
                ", navn='" + navn + '\'' +
                '}';
    }
}