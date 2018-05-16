package com.tunisair.khawla.tunisair;

public class Reponse {

    private  String dataRep;
    private String  Reponce;
    private String idEnvoi;



    public Reponse() {
    }

    public Reponse(String dataRep, String reponce, String idEnvoi) {
        this.dataRep = dataRep;
        Reponce = reponce;
        this.idEnvoi = idEnvoi;
    }

    public String getDataRep() {
        return dataRep;
    }

    public void setDataRep(String dataRep) {
        this.dataRep = dataRep;
    }

    public String getReponce() {
        return Reponce;
    }

    public void setReponce(String reponce) {
        Reponce = reponce;
    }

    public String getIdEnvoi() {
        return idEnvoi;
    }

    public void setIdEnvoi(String idEnvoi) {
        this.idEnvoi = idEnvoi;
    }
}
