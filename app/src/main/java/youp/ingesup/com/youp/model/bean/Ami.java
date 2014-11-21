/**
 * File generated by Magnet rest2mobile 1.1 - 20 nov. 2014 18:16:37
 * @see {@link http://developer.magnet.com}
 */

package youp.ingesup.com.youp.model.bean;


/**
 * Generated from json example
{
  "Utilisateur_Id" : 1,
  "Pseudo" : "samplestring2",
  "PhotoChemin" : "samplestring3"
}

 */

public class Ami {

  
@com.google.gson.annotations.SerializedName("PhotoChemin")
  private String photoChemin;

  
@com.google.gson.annotations.SerializedName("Pseudo")
  private String pseudo;

  
@com.google.gson.annotations.SerializedName("Utilisateur_Id")
  private Integer utilisateur_Id;

  public String getPhotoChemin() {
    return photoChemin;
  }
  public String getPseudo() {
    return pseudo;
  }
  public Integer getUtilisateur_Id() {
    return utilisateur_Id;
  }

  /**
  * Builder for Ami
  **/
  public static class AmiBuilder {
    private Ami toBuild = new Ami();

    public AmiBuilder() {
    }

    public Ami build() {
      return toBuild;
    }

    public AmiBuilder photoChemin(String value) {
      toBuild.photoChemin = value;
      return this;
    }
    public AmiBuilder pseudo(String value) {
      toBuild.pseudo = value;
      return this;
    }
    public AmiBuilder utilisateur_Id(Integer value) {
      toBuild.utilisateur_Id = value;
      return this;
    }
  }
}