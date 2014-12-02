package youp.ingesup.com.youp.model.bean;

/**
 * Created by Damiano on 02/12/2014.
 */
public class Participation {

    @com.google.gson.annotations.SerializedName("ParticipationId")
    private int ParticipationId; //: 4,

    @com.google.gson.annotations.SerializedName("EvenementId")
    private int EvenementId; //": 10211,

    @com.google.gson.annotations.SerializedName("UtilisateurId")
    private int UtilisateurId; //": 10240,

    @com.google.gson.annotations.SerializedName("Pseudo")
    private String Pseudo;

    @com.google.gson.annotations.SerializedName("ImageUrl")
    private String ImageUrl;

    @com.google.gson.annotations.SerializedName("DateInscription")
    private String DateInscription;

    @com.google.gson.annotations.SerializedName("DateAnnulation")
    private String DateAnnulation;

    @com.google.gson.annotations.SerializedName("Annulation")
    private Boolean Annulation;


    public int getParticipationId() {
        return ParticipationId;
    }

    public void setParticipationId(int participationId) {
        ParticipationId = participationId;
    }

    public int getEvenementId() {
        return EvenementId;
    }

    public void setEvenementId(int evenementId) {
        EvenementId = evenementId;
    }

    public String getDateAnnulation() {
        return DateAnnulation;
    }

    public void setDateAnnulation(String dateAnnulation) {
        DateAnnulation = dateAnnulation;
    }

    public Boolean getAnnulation() {
        return Annulation;
    }

    public void setAnnulation(Boolean annulation) {
        Annulation = annulation;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public void setPseudo(String pseudo) {
        Pseudo = pseudo;
    }

    public int getUtilisateurId() {
        return UtilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        UtilisateurId = utilisateurId;
    }

    public String getDateInscription() {
        return DateInscription;
    }

    public void setDateInscription(String dateInscription) {
        DateInscription = dateInscription;
    }
}
