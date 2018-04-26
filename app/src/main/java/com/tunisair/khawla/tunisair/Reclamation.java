package com.tunisair.khawla.tunisair;

public class Reclamation {
    private String identif;
    private String datrec;
    private String typerec;
    private String numvol;
    private String datvol;
    private String refebillet;
    private String numtecket;
    private String description;

    public Reclamation() {
    }

    public Reclamation(String identif, String datrec, String typerec, String numvol, String datvol, String refebillet, String numtecket, String description) {
        this.identif = identif;
        this.datrec = datrec;
        this.typerec = typerec;
        this.numvol = numvol;
        this.datvol = datvol;
        this.refebillet = refebillet;
        this.numtecket = numtecket;
        this.description = description;
    }

    public String getIdentif() {
        return identif;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public String getDatrec() {
        return datrec;
    }

    public void setDatrec(String datrec) {
        this.datrec = datrec;
    }

    public String getTyperec() {
        return typerec;
    }

    public void setTyperec(String typerec) {
        this.typerec = typerec;
    }

    public String getNumvol() {
        return numvol;
    }

    public void setNumvol(String numvol) {
        this.numvol = numvol;
    }

    public String getDatvol() {
        return datvol;
    }

    public void setDatvol(String datvol) {
        this.datvol = datvol;
    }

    public String getRefebillet() {
        return refebillet;
    }

    public void setRefebillet(String refebillet) {
        this.refebillet = refebillet;
    }

    public String getNumtecket() {
        return numtecket;
    }

    public void setNumtecket(String numtecket) {
        this.numtecket = numtecket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
