package com.tunisair.khawla.tunisair;

/**
 * Created by khawla on 29/03/2018.
 */

public class User {

    private String name;
    private String prenoms;
    private String email;
    private String pass;
    private String confirme;
    private String pasport;
    private String adrDO;
    private String villes;
    private String postale;
    private String teldom;
    private String telprof;
    private String telmobil;
    private String faxs;
    private String soc;
    private String fonc;
    private String code;
    private String pays;
    private String nationalité;


    public User(String name1, String s, String name, String prenoms, String email, String pass, String pasport, String adrDO, String villes, String postale, String co, String teldom, String telmobil, String telprof, String faxs, String soc, String fonc) {

    }

    public User(String name, String prenoms, String email, String pass, String pasport, String adrDO, String villes, String postale, String teldom, String telprof, String telmobil, String faxs, String soc, String fonc) {
        this.name = name;
        this.prenoms = prenoms;
        this.email = email;
        this.pass = pass;
        this.pasport = pasport;
        this.adrDO = adrDO;
        this.villes = villes;
        this.postale = postale;
        this.teldom = teldom;
        this.telprof = telprof;
        this.telmobil = telmobil;
        this.faxs = faxs;
        this.soc = soc;
        this.fonc = fonc;
        this.code = code;
        this.pays = pays;
        this.nationalité = nationalité;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirme() {
        return confirme;
    }

    public void setConfirme(String confirme) {
        this.confirme = confirme;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getAdrDO() {
        return adrDO;
    }

    public void setAdrDO(String adrDO) {
        this.adrDO = adrDO;
    }

    public String getVilles() {
        return villes;
    }

    public void setVilles(String villes) {
        this.villes = villes;
    }

    public String getPostale() {
        return postale;
    }

    public void setPostale(String postale) {
        this.postale = postale;
    }

    public String getTeldom() {
        return teldom;
    }

    public void setTeldom(String teldom) {
        this.teldom = teldom;
    }

    public String getTelprof() {
        return telprof;
    }

    public void setTelprof(String telprof) {
        this.telprof = telprof;
    }

    public String getTelmobil() {
        return telmobil;
    }

    public void setTelmobil(String telmobil) {
        this.telmobil = telmobil;
    }

    public String getFaxs() {
        return faxs;
    }

    public void setFaxs(String faxs) {
        this.faxs = faxs;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getFonc() {
        return fonc;
    }

    public void setFonc(String fonc) {
        this.fonc = fonc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNationalité() {
        return nationalité;
    }

    public void setNationalité(String nationalité) {
        this.nationalité = nationalité;
    }


}
