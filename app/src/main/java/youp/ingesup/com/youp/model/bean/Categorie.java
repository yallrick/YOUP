package youp.ingesup.com.youp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public class Categorie {

    @SerializedName("Categorie_id")
    private int id;

    @SerializedName("Libelle")
    private String label;

    public Categorie(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
