package youp.ingesup.com.youp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent del Valle on 20/11/2014.
 */
public class Topic {

    @SerializedName("Topic_id")
    private int id;

    @SerializedName("Sujet_id")
    private int sujetId;

    @SerializedName("Nom")
    private String name;

    @SerializedName("DescriptifTopic")
    private String descriptionTopic;

    @SerializedName("DateCreation")
    private String dateCreation;

    @SerializedName("Resolu")
    private boolean isResolve;

    @SerializedName("Utilisateur_id")
    private int idUser;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSujetId() {
        return sujetId;
    }

    public void setSujetId(int sujetId) {
        this.sujetId = sujetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionTopic() {
        return descriptionTopic;
    }

    public void setDescriptionTopic(String descriptionTopic) {
        this.descriptionTopic = descriptionTopic;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isResolve() {
        return isResolve;
    }

    public void setResolve(boolean isResolve) {
        this.isResolve = isResolve;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
