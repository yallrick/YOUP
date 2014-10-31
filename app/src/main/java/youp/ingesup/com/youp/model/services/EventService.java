package youp.ingesup.com.youp.model.services;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import youp.ingesup.com.youp.model.bean.Categorie;
import youp.ingesup.com.youp.model.bean.Evenement;

/**
 * Created by Vincent del Valle on 31/10/2014.
 *
 * @see <a href="http://youp-evenementapi.azurewebsites.net/help">http://youp-evenementapi.azurewebsites.net/help</a>
 */
public interface EventService {

    //*********************************//
    //********* CATÉGORIES ************//
    //*********************************//

    @GET("/api/categorie")
    void getCategories(Callback<List<Categorie>> callback);

    @GET("/api/categorie/{id}")
    void getCategories(@Path("id") String id, Callback<Categorie> callback);




    //*********************************//
    //********* ÉVÈNEMENTS ************//
    //*********************************//


    /**
     *
     * @param dateSearch
     * @param maxResult
     * @param categorie
     * @param textSearch
     * @param maxId
     * @param orderBy
     * @return
     *
     * @see <a href="http://youp-evenementapi.azurewebsites.net/Help/Api/GET-api-Evenement_date_search_max_result_categorie_text_search_max_id_orderby">http://youp-evenementapi.azurewebsites.net/Help/Api/GET-api-Evenement_date_search_max_result_categorie_text_search_max_id_orderby</a>
     */
    @GET("api/Evenement?date_search={date_search}&max_result={max_result}&categorie={categorie}&text_search={text_search}&max_id={max_id}&orderby={orderby}")
    void getEvenements(@Path("date_search") String dateSearch,
                         @Path("max_result") String maxResult,
                         @Path("categorie") String categorie,
                         @Path("text_search") String textSearch,
                         @Path("max_id") String maxId,
                         @Path("orderby") String orderBy,
                         Callback<List<Evenement>> callback);


    @GET("api/Evenement?id_profil={id_profil}")
    void getEvements(@Path("id_profil") String idProfile,
                     Callback<List<Evenement>> callback);





}
