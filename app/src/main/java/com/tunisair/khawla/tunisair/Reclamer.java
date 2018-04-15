package com.tunisair.khawla.tunisair;

public class Reclamer {
    private String iden;
    private String type;
    private String dec;
    private String num;
    private String numvol;
    private String refe;
    private String tic;

   // public Reclamer(String iden, String type, String dec, String num, String numvol, String refe, String tic) {
   // }

    public Reclamer(String iden, String type, String dec, String num, String numvol, String refe, String tic) {
        this.iden = iden;
        this.type = type;
        this.dec = dec;
        this.num = num;
        this.numvol = numvol;
        this.refe = refe;
        this.tic = tic;
    }

    public String getIden() {
        return iden;
    }

    public String getType() {
        return type;
    }

    public String getDec() {
        return dec;
    }

    public String getNum() {
        return num;
    }

    public String getNumvol() {
        return numvol;
    }

    public String getRefe() {
        return refe;
    }

    public String getTic() {
        return tic;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setNumvol(String numvol) {
        this.numvol = numvol;
    }

    public void setRefe(String refe) {
        this.refe = refe;
    }

    public void setTic(String tic) {
        this.tic = tic;
    }
}
