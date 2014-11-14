package youp.ingesup.com.youp.model.services;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.bean.User;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public interface UserService {

    @GET("/api/User/{id}")
    String getUser(@Path("id") String id, Callback<User> callback);

    @POST("/api/Auth?Email={Email}&Pass={Pass}&Device=Android")
    void login(@Path("Email") String username, @Path("Pass") String password, Callback<User> callback);

    @FormUrlEncoded
    @POST("/api/User")
    void createUser(@Field("Pseudo") String pseudo,
                    @Field("MotDePasse") String pass,
                    @Field("Nom") String nom,
                    @Field("Prenom") String prenom,
                    @Field("Sexe") boolean isMale,
                    @Field("AdresseMail") String mail,
                    @Field("DateNaissance") String dateNaissance,
                    @Field("Ville") String ville,
                    @Field("CodePostal") String codePostal,
                    Callback<User> callback);

    @GET("api/Friend/{id}")
    void getFriends(@Path("id") String idUser, Callback<Friend[]> callback);

    // TODO create



}
