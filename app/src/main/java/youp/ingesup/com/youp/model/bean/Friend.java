package youp.ingesup.com.youp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class Friend {

    @SerializedName("Utilisateur_Id")
    private int id;

    @SerializedName("Pseudo")
    private String pseudo;

    @SerializedName("PhotoChemin")
    private String urlPhoto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
