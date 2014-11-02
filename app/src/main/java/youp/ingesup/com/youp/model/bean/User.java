package youp.ingesup.com.youp.model.bean;

/**
 * Created by Damiano on 31/10/2014.
 */
public class User {

    private int id;
    private String nom;

    public User(int _id, String _nom)
    {
        this.id = _id;
        this.nom = _nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String _nom) {
        this.nom = _nom;
    }

}
