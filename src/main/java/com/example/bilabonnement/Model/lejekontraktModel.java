package com.example.bilabonnement.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class lejekontraktModel {

    private int kontraktID;
    private int kundeID;
    private int bilID;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String pickupSted;
    private String afleverSted;
    private BigDecimal pris;

    public lejekontraktModel(){
    }

    public lejekontraktModel(int kontraktID, int kundeID, int bilID, LocalDate startDato, LocalDate slutDato, String pickupSted, String afleverSted, BigDecimal pris) {
        this.kontraktID = kontraktID;
        this.kundeID = kundeID;
        this.bilID = bilID;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.pickupSted = pickupSted;
        this.afleverSted = afleverSted;
        this.pris = pris;
    }

    /* ---------------GETTERS--------------- */

    public int getKontraktID() {return kontraktID;}
    public int getKundeID(){ return kundeID;}
    public int getBilID() {return bilID;}
    public LocalDate getStartDato() {return startDato;}
    public LocalDate getSlutDato() {return slutDato;}
    public String getPickupSted() {return pickupSted;}
    public String getAfleverSted() {return afleverSted;}
    public BigDecimal getPris() {return pris;}

    /* ---------------SETTERS---------------*/

    public void setKontraktID(int kontraktID){ this.kontraktID = kontraktID;}
    public void setKundeID(int kundeID){this.kundeID = kundeID;}
    public void setBilID(int bilID){this.bilID = bilID;}
    public void setStartDato(LocalDate startDato){this.startDato = startDato;}
    public void setSlutDato(LocalDate slutDato){this.slutDato = slutDato;}
    public void setPickupSted(String pickupSted){this.pickupSted = pickupSted;}
    public void setAfleverSted(String afleverSted){this.afleverSted = afleverSted;}
    public void setPris(BigDecimal pris){this.pris = pris;}

}
