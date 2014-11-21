package youp.ingesup.com.youp.model.bean;

import java.util.List;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public class Evenement {


    @com.google.gson.annotations.SerializedName("Categorie")
    private Categorie categorie;


    @com.google.gson.annotations.SerializedName("CreateDate")
    private String createDate;


    @com.google.gson.annotations.SerializedName("DateFinInscription")
    private String dateFinInscription;

    @com.google.gson.annotations.SerializedName("DateModification")
    private String dateModification;

    @com.google.gson.annotations.SerializedName("EtatEvenement")
    private EtatEvenement etatEvenement;


    @com.google.gson.annotations.SerializedName("EventAdresse")
    private Adresse eventAdresse;


    @com.google.gson.annotations.SerializedName("Galleries")
    private List<Gallery> galleries;


    @com.google.gson.annotations.SerializedName("HashTag")
    private List<java.lang.String> hashTag;


    @com.google.gson.annotations.SerializedName("Id")
    private Integer id;


    @com.google.gson.annotations.SerializedName("MinimumParticipant")
    private Integer minimumParticipant;


    @com.google.gson.annotations.SerializedName("Organisateur_id")
    private Integer organisateurId;

    @com.google.gson.annotations.SerializedName("Payant")
    private Boolean payant;


    @com.google.gson.annotations.SerializedName("Price")
    private Double price;



    @com.google.gson.annotations.SerializedName("Public")
    private Boolean thePublic;



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


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDateFinInscription() {
        return dateFinInscription;
    }

    public void setDateFinInscription(String dateFinInscription) {
        this.dateFinInscription = dateFinInscription;
    }

    public String getDateModification() {
        return dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public EtatEvenement getEtatEvenement() {
        return etatEvenement;
    }

    public void setEtatEvenement(EtatEvenement etatEvenement) {
        this.etatEvenement = etatEvenement;
    }

    public Adresse getEventAdresse() {
        return eventAdresse;
    }

    public void setEventAdresse(Adresse eventAdresse) {
        this.eventAdresse = eventAdresse;
    }

    public List<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }

    public List<String> getHashTag() {
        return hashTag;
    }

    public void setHashTag(List<String> hashTag) {
        this.hashTag = hashTag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinimumParticipant() {
        return minimumParticipant;
    }

    public void setMinimumParticipant(Integer minimumParticipant) {
        this.minimumParticipant = minimumParticipant;
    }

    public Integer getOrganisateurId() {
        return organisateurId;
    }

    public void setOrganisateurId(Integer organisateurId) {
        this.organisateurId = organisateurId;
    }

    public Boolean getPayant() {
        return payant;
    }

    public void setPayant(Boolean payant) {
        this.payant = payant;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getThePublic() {
        return thePublic;
    }

    public void setThePublic(Boolean thePublic) {
        this.thePublic = thePublic;
    }
}
