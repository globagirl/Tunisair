package com.tunisair.khawla.tunisair;

public class Vols {
    private String IdV;
    private String PaysAre;
    private String PaysDep;
    private String DatAre;
    private String DateDeo;
    private String NB_places;
    private String Prix_A;
    private String Prix_A_R;
    private String Prix_Cal_A;
    private String Prix_Cal_A_R;

    public Vols() {
    }

    public Vols(String idV, String paysAre, String paysDep, String datAre, String dateDeo, String NB_places, String prix_A, String prix_A_R, String prix_Cal_A, String prix_Cal_A_R) {
        IdV = idV;
        PaysAre = paysAre;
        PaysDep = paysDep;
        DatAre = datAre;
        DateDeo = dateDeo;
        this.NB_places = NB_places;
        Prix_A = prix_A;
        Prix_A_R = prix_A_R;
        Prix_Cal_A = prix_Cal_A;
        Prix_Cal_A_R = prix_Cal_A_R;
    }

    public String getIdV() {
        return IdV;
    }

    public void setIdV(String idV) {
        IdV = idV;
    }

    public String getPaysAre() {
        return PaysAre;
    }

    public void setPaysAre(String paysAre) {
        PaysAre = paysAre;
    }

    public String getPaysDep() {
        return PaysDep;
    }

    public void setPaysDep(String paysDep) {
        PaysDep = paysDep;
    }

    public String getDatAre() {
        return DatAre;
    }

    public void setDatAre(String datAre) {
        DatAre = datAre;
    }

    public String getDateDeo() {
        return DateDeo;
    }

    public void setDateDeo(String dateDeo) {
        DateDeo = dateDeo;
    }

    public String getNB_places() {
        return NB_places;
    }

    public void setNB_places(String NB_places) {
        this.NB_places = NB_places;
    }

    public String getPrix_A() {
        return Prix_A;
    }

    public void setPrix_A(String prix_A) {
        Prix_A = prix_A;
    }

    public String getPrix_A_R() {
        return Prix_A_R;
    }

    public void setPrix_A_R(String prix_A_R) {
        Prix_A_R = prix_A_R;
    }

    public String getPrix_Cal_A() {
        return Prix_Cal_A;
    }

    public void setPrix_Cal_A(String prix_Cal_A) {
        Prix_Cal_A = prix_Cal_A;
    }

    public String getPrix_Cal_A_R() {
        return Prix_Cal_A_R;
    }

    public void setPrix_Cal_A_R(String prix_Cal_A_R) {
        Prix_Cal_A_R = prix_Cal_A_R;
    }
}
