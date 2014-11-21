package youp.ingesup.com.youp.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.UserService;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountProfileFragment extends Fragment {

    private UserService userService;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_account_profile, container, false);

        Integer profile_id = MainAccountFragment.profileID;
        if(profile_id == 0 && Auth.getInstance().getUser() != null)
            profile_id = Auth.getInstance().getUser().getId();

        RestAdapter serviceUserBuilder = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
        userService = serviceUserBuilder.create(UserService.class);

        userService.getUser(profile_id.toString(),new Callback<User>(){

            @Override
            public void success(User profile, Response response) {
                user = profile;
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Problème pour récupérer des informations du profil.", Toast.LENGTH_LONG).show();
            }
        });

        ChargementDonnees();

        return root;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    private void ChargementDonnees()
    {

    }
}
