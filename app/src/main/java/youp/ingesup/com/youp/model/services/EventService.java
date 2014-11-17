package youp.ingesup.com.youp.model.services;




import java.util.List;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import youp.ingesup.com.youp.model.bean.Categorie;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.bean.Location;

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
    @GET("/api/Evenement?date_search={date_search}&max_result={max_result}&categorie={categorie}&text_search={text_search}&max_id={max_id}&orderby={orderby}")
    void getEvents(@Path("date_search") String dateSearch,
                   @Path("max_result") String maxResult,
                   @Path("categorie") String categorie,
                   @Path("text_search") String textSearch,
                   @Path("max_id") String maxId,
                   @Path("orderby") String orderBy,
                   Callback<List<Evenement>> callback);

    @GET("/api/Evenement")
    void getEvents(Callback<List<Evenement>> callback);


    @GET("/api/Evenement?id_profil={id_profil}")
    void getEvents(@Path("id_profil") String idProfile,
                     Callback<List<Evenement>> callback);


    @GET("/api/Evenement/{id}")
    void getEvent(@Path("id") String idEvent, Callback<Evenement> callback);

    @PUT("/api/Evenement/{id}?prenium={prenium}&end_inscription={end_inscription}&total_people={total_people}&description={description}")
    void setEvent(@Path("id") String eventId,
                  @Path("prenium") boolean isPremium,
                  @Path("end_inscription") String dateFinInscription,
                  @Path("total_people") String totalPersonnes,
                  @Path("description") String description);


    @POST("/api/Evenement?idEvenement={idEvenement}&idProfil={idProfil}")
    void joinEvent(@Path("idEvenement") String idEvent,
                   @Path("idProfil") String idProfile);

    @DELETE("/api/Evenement/{id}?id_profil={id_profil}")
    void destroyEvent(@Path("id") String id,
                      @Path("id_profil") String idProfile);

    @POST("/api/Evenement?end_inscription={end_inscription}&date_event={date_event}&total_people={total_people}&description={description}&title={title}&prenium={prenium}&payant={payant}&isPublic={isPublic}")
    void createEvent(@Path("end_inscription") String dateFinInscription,
                     @Path("date_event") String dateEvent,
                     @Path("total_people") String totalPersonnes,
                     @Path("description") String description,
                     @Path("title") String titre,
                     @Path("prenium") boolean isPremium,
                     @Path("payant") boolean isPayant,
                     @Path("isPublic") boolean isPublic);


    @GET("/api/EvenementEtat")
    void getEventStates();

    @GET("/api/EvenementEtat/{id}")
    void getState(@Path("id") String id);

    @GET("/api/Lieu")
    void getLocations();

    @GET("/api/Lieu/{id}")
    void getLocation(@Path("id") String id, Callback<Location> callback);


}
