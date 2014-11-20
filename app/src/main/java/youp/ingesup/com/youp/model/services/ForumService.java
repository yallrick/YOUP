package youp.ingesup.com.youp.model.services;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import youp.ingesup.com.youp.model.bean.Topic;

/**
 * Created by Vincent del Valle on 20/11/2014.
 */
public interface ForumService {

    @GET("/api/Topics")
    void getTopics(Callback<List<Topic>> callback);


    @GET("/api/Topics")
    void getTopic(@Query("id") String id, Callback<List<Topic>> callback);


    @POST("/api/Message")
    void sendMessage(@Query("Topic_id") String idTopic,
                     @Query("Utilisateur_id") String idUser,
                     @Query("ContenuMessage") String content,
                     Callback<Boolean> callback);


}
