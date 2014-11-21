package youp.ingesup.com.youp.model.services;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import youp.ingesup.com.youp.model.bean.Message;
import youp.ingesup.com.youp.model.bean.Topic;

/**
 * Created by Vincent del Valle on 20/11/2014.
 */
public interface ForumService {

    @GET("/api/Topics")
    void getTopics(Callback<List<Topic>> callback);


    @GET("/api/Topics")
    void getTopic(@Query("id") String id, Callback<List<Topic>> callback);


    @GET("/api/MessageTopic/{IDTopic}")
    void getMessages(@Path("IDTopic") String idTopic, Callback<Message[]> callback);

    @FormUrlEncoded
    @POST("/api/Message")
    void sendMessage(@Field("Topic_id") String idTopic,
                     @Field("Utilisateur_id") String idUser,
                     @Field("ContenuMessage") String content,
                     @Field("DatePoste") String date,
                     Callback<Boolean> callback);


}
