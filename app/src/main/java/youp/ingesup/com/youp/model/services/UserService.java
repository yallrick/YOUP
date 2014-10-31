package youp.ingesup.com.youp.model.services;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vincent del Valle on 31/10/2014.
 */
public interface UserService {

    @GET("/users/{id}?token={token}")
    String getUser(@Path("id") String id, @Path("token") String token);

    @POST("/users/login?username={username}&password={password}")
    String login(@Path("username") String username, @Path("password") String password);

    // TODO create

    // TODO delete

    // TODO create



}
