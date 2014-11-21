package youp.ingesup.com.youp.model.bean;

import java.util.List;

/**
 * Created by Damiano on 31/10/2014.
 */
public class User {



    @com.google.gson.annotations.SerializedName("Actif")
    private Boolean actif;


    @com.google.gson.annotations.SerializedName("AdresseMail")
    private String adresseMail;


    @com.google.gson.annotations.SerializedName("Amis")
    private List<Ami> amis;


    @com.google.gson.annotations.SerializedName("Categories")
    private List<Category> categories;


    @com.google.gson.annotations.SerializedName("CodePostal")
    private String codePostal;


    @com.google.gson.annotations.SerializedName("DateInscription")
    private String dateInscription;


    @com.google.gson.annotations.SerializedName("DateNaissance")
    private String dateNaissance;


    @com.google.gson.annotations.SerializedName("Metier")
    private String metier;


    @com.google.gson.annotations.SerializedName("MotDePasse")
    private String motDePasse;


    @com.google.gson.annotations.SerializedName("Nom")
    private String nom;


    @com.google.gson.annotations.SerializedName("Partenaire")
    private Boolean partenaire;


    @com.google.gson.annotations.SerializedName("PhotoChemin")
    private String photoChemin;


    @com.google.gson.annotations.SerializedName("Prenom")
    private String prenom;


    @com.google.gson.annotations.SerializedName("Presentation")
    private String presentation;


    @com.google.gson.annotations.SerializedName("Pseudo")
    private String pseudo;


    @com.google.gson.annotations.SerializedName("Sexe")
    private Boolean sexe;


    @com.google.gson.annotations.SerializedName("Situation")
    private String situation;


    @com.google.gson.annotations.SerializedName("Token")
    private String token;


    @com.google.gson.annotations.SerializedName("Utilisateur_Id")
    private Integer id;


    @com.google.gson.annotations.SerializedName("Ville")
    private String ville;

    public User(Integer _id, String _nom)
    {
        this.id = _id;
        this.nom = _nom;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public List<Ami> getAmis() {
        return amis;
    }

    public void setAmis(List<Ami> amis) {
        this.amis = amis;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Boolean partenaire) {
        this.partenaire = partenaire;
    }

    public String getPhotoChemin() {
        return photoChemin;
    }

    public void setPhotoChemin(String photoChemin) {
        this.photoChemin = photoChemin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Boolean getSexe() {
        return sexe;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
