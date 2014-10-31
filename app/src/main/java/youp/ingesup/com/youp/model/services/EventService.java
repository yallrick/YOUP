package youp.ingesup.com.youp.model.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

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
    void getCategories(Callback<String[]> callback);

    @GET("/api/categorie/{id}")
    String getCategories(@Path("id") String id);




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
    String getEvenements(@Path("date_search") String dateSearch,
                         @Path("max_result") String maxResult,
                         @Path("categorie") String categorie,
                         @Path("text_search") String textSearch,
                         @Path("max_id") String maxId,
                         @Path("orderby") String orderBy);






}
