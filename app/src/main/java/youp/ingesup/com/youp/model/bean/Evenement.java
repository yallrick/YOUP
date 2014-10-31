package youp.ingesup.com.youp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public class Evenement {

    @SerializedName("Evenement_id")
    private int id;

    @SerializedName("LieuEvenement_id")
    private int lieuId;

    @SerializedName("Categorie_id")
    private int categorieId;

    @SerializedName("DateEvenement")
    private String date;

    @SerializedName("TitreEvenement")
    private String titre;

    @SerializedName("MaximumParticipant")
    private int maxPersonne;

    @SerializedName("Statut")
    private String statut;

    @SerializedName("Prix")
    private int prix;

    @SerializedName("Premium")
    private boolean premium;

    @SerializedName("DateMiseEnAvant")
    private String dateMiseEnAvant;

    @SerializedName("Etat_id")
    private int etatId;

    @SerializedName("EvenementPhoto_id")
    private int photoId;

    @SerializedName("Adresse")
    private String adresse;

    public Evenement(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLieuId() {
        return lieuId;
    }

    public void setLieuId(int lieuId) {
        this.lieuId = lieuId;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getMaxPersonne() {
        return maxPersonne;
    }

    public void setMaxPersonne(int maxPersonne) {
        this.maxPersonne = maxPersonne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getDateMiseEnAvant() {
        return dateMiseEnAvant;
    }

    public void setDateMiseEnAvant(String dateMiseEnAvant) {
        this.dateMiseEnAvant = dateMiseEnAvant;
    }

    public int getEtatId() {
        return etatId;
    }

    public void setEtatId(int etatId) {
        this.etatId = etatId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
