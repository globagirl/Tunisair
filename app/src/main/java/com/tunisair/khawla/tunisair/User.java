package com.tunisair.khawla.tunisair;

/**
 * Created by khawla on 29/03/2018.
 */

public class User {

    private String nom;
    private String prenom;
    private String genre;
    private String naisence;
    private String email;
    private String pass;
    private String pasport;
    private String villes;
    private String postale;
    private String adrDO;
    private String nationalite;
    private String pays;
    private String teldom;
    private String telprof;
    private String telmobil;
    private String telfaxs;
    private String societe;
    private String fonction;
    private String num_vol;
    private String dat_vol;
    private String num_bielet;
    private String agence;
    private String type_ad;
    private String autre_prog;
    private String langue;
    private String agence_habit;
    private String point_vent;
    private String site_vent;
    private String prefere;
    private String classe;
    private String mode_pay;
    private String habitude;
    private String besoin;
    private boolean accepte_mail;

    public User() {
    }

    public User(String nom, String prenom, String genre, String naisence, String email, String pass, String pasport, String villes, String postale, String adrDO, String nationalite, String pays, String teldom, String telprof, String telmobil, String telfaxs, String societe, String fonction, String num_vol, String dat_vol, String num_bielet, String agence, String type_ad, String autre_prog, String langue, String agence_habit, String point_vent, String site_vent, String prefere, String classe, String mode_pay, String habitude, String besoin, boolean accepte_mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.naisence = naisence;
        this.email = email;
        this.pass = pass;
        this.pasport = pasport;
        this.villes = villes;
        this.postale = postale;
        this.adrDO = adrDO;
        this.nationalite = nationalite;
        this.pays = pays;
        this.teldom = teldom;
        this.telprof = telprof;
        this.telmobil = telmobil;
        this.telfaxs = telfaxs;
        this.societe = societe;
        this.fonction = fonction;
        this.num_vol = num_vol;
        this.dat_vol = dat_vol;
        this.num_bielet = num_bielet;
        this.agence = agence;
        this.type_ad = type_ad;
        this.autre_prog = autre_prog;
        this.langue = langue;
        this.agence_habit = agence_habit;
        this.point_vent = point_vent;
        this.site_vent = site_vent;
        this.prefere = prefere;
        this.classe = classe;
        this.mode_pay = mode_pay;
        this.habitude = habitude;
        this.besoin = besoin;
        this.accepte_mail = accepte_mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNaisence() {
        return naisence;
    }

    public void setNaisence(String naisence) {
        this.naisence = naisence;
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

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
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

    public String getAdrDO() {
        return adrDO;
    }

    public void setAdrDO(String adrDO) {
        this.adrDO = adrDO;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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

    public String getTelfaxs() {
        return telfaxs;
    }

    public void setTelfaxs(String telfaxs) {
        this.telfaxs = telfaxs;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getNum_vol() {
        return num_vol;
    }

    public void setNum_vol(String num_vol) {
        this.num_vol = num_vol;
    }

    public String getDat_vol() {
        return dat_vol;
    }

    public void setDat_vol(String dat_vol) {
        this.dat_vol = dat_vol;
    }

    public String getNum_bielet() {
        return num_bielet;
    }

    public void setNum_bielet(String num_bielet) {
        this.num_bielet = num_bielet;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getType_ad() {
        return type_ad;
    }

    public void setType_ad(String type_ad) {
        this.type_ad = type_ad;
    }

    public String getAutre_prog() {
        return autre_prog;
    }

    public void setAutre_prog(String autre_prog) {
        this.autre_prog = autre_prog;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getAgence_habit() {
        return agence_habit;
    }

    public void setAgence_habit(String agence_habit) {
        this.agence_habit = agence_habit;
    }

    public String getPoint_vent() {
        return point_vent;
    }

    public void setPoint_vent(String point_vent) {
        this.point_vent = point_vent;
    }

    public String getSite_vent() {
        return site_vent;
    }

    public void setSite_vent(String site_vent) {
        this.site_vent = site_vent;
    }

    public String getPrefere() {
        return prefere;
    }

    public void setPrefere(String prefere) {
        this.prefere = prefere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMode_pay() {
        return mode_pay;
    }

    public void setMode_pay(String mode_pay) {
        this.mode_pay = mode_pay;
    }

    public String getHabitude() {
        return habitude;
    }

    public void setHabitude(String habitude) {
        this.habitude = habitude;
    }

    public String getBesoin() {
        return besoin;
    }

    public void setBesoin(String besoin) {
        this.besoin = besoin;
    }

    public boolean isAccepte_mail() {
        return accepte_mail;
    }

    public void setAccepte_mail(boolean accepte_mail) {
        this.accepte_mail = accepte_mail;
    }
}
