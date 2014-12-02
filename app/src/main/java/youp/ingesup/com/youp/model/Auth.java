package youp.ingesup.com.youp.model;

import youp.ingesup.com.youp.model.bean.User;

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

    /**
     * Crée une nouvelle instance de Auth si l'utilisateur et le token ne sont pas vide. Sinon
     * renvoi null.
     *
     * @param _user L'utilisateur connecté
     * @param _token Le token d'authentification
     */
    public static Auth getInstance(User _user, String _token) {
        if(instance == null && _user != null && _token != null && !_token.isEmpty()) {
            instance = new Auth(_user, _token);
        }
        return instance;
    }

    /**
     * Retourne l'instance de la classe auth, uniquement si elle a déjà été créée
     */
    public static Auth getInstance(){

        return instance;
    }

    /**
     * Retourne vrai si l'utilisateur en cours est authentifié.
     */
    public static boolean isLoggedIn(){

        return instance != null;
    }

    public static void logout(){
        instance = null;
    }

    public User getUser()
    {
        return this.user;
    }

    public String getToken() {
        return token;
    }
}
