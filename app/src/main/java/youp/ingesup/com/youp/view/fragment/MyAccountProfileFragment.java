package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.view.MyAccountActivity;
import youp.ingesup.com.youp.view.adapter.MyAccountFriendsAdapter;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountProfileFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

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

        Integer profile_id = ((MyAccountActivity)getActivity()).profileID;
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

        return root;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
