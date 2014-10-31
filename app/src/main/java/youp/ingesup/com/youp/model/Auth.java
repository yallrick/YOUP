package youp.ingesup.com.youp.model;

/**
 * Created by Damiano on 31/10/2014.
 */
public class Auth {

    private static Auth instance = null;;


    private User user;
    private String token;

    private Auth(User _user, String _token)
    {
        this.user = _user;
        this.token = _token;
    }

    public static Auth getInstance(User _user, String _token) {
        if(instance == null) {
            instance = new Auth(_user, _token);
        }
        return instance;
    }

    public User getUser()
    {
        return this.user;
    }

    public String getToken() {
        return token;
    }
}
