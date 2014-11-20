package youp.ingesup.com.youp.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Damiano on 31/10/2014.
 */
public class User {

    @SerializedName("Utilisateur_Id")
    private Integer id;

    @SerializedName("Pseudo")
    private String pseudo;

    @SerializedName("MotDePasse")
    private String password;

    @SerializedName("DateInscription")
    private String dateInscription;

    @SerializedName("Nom")
    private String nom;

    @SerializedName("Prenom")
    private String prenom;

    @SerializedName("Sexe")
    private boolean isMale;

    @SerializedName("CodePostal")
    private String codePostal;

    @SerializedName("PhotoChemin")
    private String photoChemin;

    @SerializedName("Situation")
    private String situation;

    @SerializedName("Actif")
    private boolean actif;

    @SerializedName("Partenaire")
    private boolean partenaire;

    @SerializedName("Presentation")
    private String presentation;

    @SerializedName("Metier")
    private String metier;

    @SerializedName("Token")
    private String token;

    @SerializedName("Amis")
    private List<User> amis;

    @SerializedName("Categories")
    private List<Categorie> categories;


    public User(Integer _id, String _nom)
    {
        this.id = _id;
        this.nom = _nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
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

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPhotoChemin() {
        return photoChemin;
    }

    public void setPhotoChemin(String photoChemin) {
        this.photoChemin = photoChemin;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isPartenaire() {
        return partenaire;
    }

    public void setPartenaire(boolean partenaire) {
        this.partenaire = partenaire;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<User> getAmis() {
        return amis;
    }

    public void setAmis(List<User> amis) {
        this.amis = amis;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }
}
