package youp.ingesup.com.youp.model;

import retrofit.http.GET;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public interface EventService {

    /*********************************/
    /********* CATÉGORIES ************/
    /*********************************/


    @GET("/api/categorie")
    String getCategories();


}
