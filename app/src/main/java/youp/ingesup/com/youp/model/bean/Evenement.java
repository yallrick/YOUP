package youp.ingesup.com.youp.model.bean;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public class Evenement {

    @com.google.gson.annotations.SerializedName("Adresse")
    private Adresse adresse;


    @com.google.gson.annotations.SerializedName("Categorie_Libelle")
    private String categorie_Libelle;


    @com.google.gson.annotations.SerializedName("DateEvenement")
    private String dateEvenement;


    @com.google.gson.annotations.SerializedName("DateMiseEnAvant")
    private String dateMiseEnAvant;


    @com.google.gson.annotations.SerializedName("DescriptionEvenement")
    private String descriptionEvenement;


    @com.google.gson.annotations.SerializedName("Etat")
    private String etat;


    @com.google.gson.annotations.SerializedName("Evenement_id")
    private Integer evenement_id;


    @com.google.gson.annotations.SerializedName("ImageUrl")
    private String imageUrl;


    @com.google.gson.annotations.SerializedName("MaximumParticipant")
    private Integer maximumParticipant;


    @com.google.gson.annotations.SerializedName("OrganisateurImageUrl")
    private String organisateurImageUrl;


    @com.google.gson.annotations.SerializedName("OrganisateurPseudo")
    private String organisateurPseudo;


    @com.google.gson.annotations.SerializedName("Premium")
    private Boolean premium;


    @com.google.gson.annotations.SerializedName("Prix")
    private Integer prix;


    @com.google.gson.annotations.SerializedName("Statut")
    private String statut;


    @com.google.gson.annotations.SerializedName("TitreEvenement")
    private String titreEvenement;


    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getCategorie_Libelle() {
        return categorie_Libelle;
    }

    public void setCategorie_Libelle(String categorie_Libelle) {
        this.categorie_Libelle = categorie_Libelle;
    }

    public String getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(String dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getDateMiseEnAvant() {
        return dateMiseEnAvant;
    }

    public void setDateMiseEnAvant(String dateMiseEnAvant) {
        this.dateMiseEnAvant = dateMiseEnAvant;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(Integer evenement_id) {
        this.evenement_id = evenement_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMaximumParticipant() {
        return maximumParticipant;
    }

    public void setMaximumParticipant(Integer maximumParticipant) {
        this.maximumParticipant = maximumParticipant;
    }

    public String getOrganisateurImageUrl() {
        return organisateurImageUrl;
    }

    public void setOrganisateurImageUrl(String organisateurImageUrl) {
        this.organisateurImageUrl = organisateurImageUrl;
    }

    public String getOrganisateurPseudo() {
        return organisateurPseudo;
    }

    public void setOrganisateurPseudo(String organisateurPseudo) {
        this.organisateurPseudo = organisateurPseudo;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getTitreEvenement() {
        return titreEvenement;
    }

    public void setTitreEvenement(String titreEvenement) {
        this.titreEvenement = titreEvenement;
    }
}
