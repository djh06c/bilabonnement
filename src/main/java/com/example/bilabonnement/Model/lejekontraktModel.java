package com.example.bilabonnement.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class lejekontraktModel {

    private int kontraktID;
    private int kundeID;
    private int bilID;
    private LocalDate startDato;
    private LocalDate slutDato;
    private int maaneder;
    private String pickupSted;
    private String afleveringsSted;
    private Double pris;

    public lejekontraktModel(){
    }

    public lejekontraktModel(int kontraktID, int kundeID, int bilID, LocalDate startDato, LocalDate slutDato, int maaneder, String pickupSted, String afleveringsSted, Double pris) {
        this.kontraktID = kontraktID;
        this.kundeID = kundeID;
        this.bilID = bilID;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maaneder = maaneder;
        this.pickupSted = pickupSted;
        this.afleveringsSted = afleveringsSted;
        this.pris = pris;
    }

    /* ---------------GETTERS--------------- */

    public int getKontraktID() {return kontraktID;}
    public int getKundeID(){ return kundeID;}
    public int getBilID() {return bilID;}
    public LocalDate getStartDato() {return startDato;}
    public LocalDate getSlutDato() {return slutDato;}
    public int getMaaneder() {return maaneder;}
    public String getPickupSted() {return pickupSted;}
    public String getAfleveringsSted() {return afleveringsSted;}
    public Double getPris() {return pris;}

    /* ---------------SETTERS---------------*/

    public void setKontraktID(int kontraktID){ this.kontraktID = kontraktID;}
    public void setKundeID(int kundeID){this.kundeID = kundeID;}
    public void setBilID(int bilID){this.bilID = bilID;}
    public void setStartDato(LocalDate startDato){this.startDato = startDato;}
    public void setSlutDato(LocalDate slutDato){this.slutDato = slutDato;}
    public void setMaaneder(int maaneder){this.maaneder = maaneder;}
    public void setPickupSted(String pickupSted){this.pickupSted = pickupSted;}
    public void setAfleveringsSted(String afleverSted){this.afleveringsSted = afleverSted;}
    public void setPris(Double pris){this.pris = pris;}
}