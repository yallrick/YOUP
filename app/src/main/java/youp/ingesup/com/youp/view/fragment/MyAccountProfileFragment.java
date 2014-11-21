package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

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

    private TextView tvPseudo;
    private TextView tvNom;
    private TextView tvPrenom;

    private ImageView imgSexe;

    private TextView tvDateNaissance;
    private TextView tvVille;
    private TextView tvMetier;
    private TextView tvDateInscription;
    private TextView tvMail;

    private TextView tvDescription;

    private ImageView imgProfil;

    private int IMAGE_MALE = R.drawable.male_icon;
    private int IMAGE_FEMALE = R.drawable.female_icon;

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

        /*** Récupération éléments écran ***/
        tvPseudo = (TextView) root.findViewById(R.id.tvPseudoProfile);
        tvNom = (TextView) root.findViewById(R.id.tvNomProfile);
        tvPrenom = (TextView) root.findViewById(R.id.tvPrenomProfile);
        imgSexe = (ImageView) root.findViewById(R.id.imgGender);
        tvDateNaissance = (TextView) root.findViewById(R.id.tvDateNaissance);
        tvVille = (TextView) root.findViewById(R.id.tvCity);
        tvMetier = (TextView) root.findViewById(R.id.tvMetier);
        tvDateInscription  = (TextView) root.findViewById(R.id.tvDateInscription);
        tvMail = (TextView) root.findViewById(R.id.tvMetier);
        tvDescription = (TextView) root.findViewById(R.id.tvPresentationProfile);
        imgProfil = (ImageView) root.findViewById(R.id.imgProfile);

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

    private void ChargementDonnees()
    {
        tvPseudo.setText(user.getPseudo());
        tvNom.setText(user.getNom());
        tvPrenom.setText(user.getPrenom());
        tvDateNaissance.setText(user.getDateNaissance());
        tvVille.setText(user.getVille());
        tvMetier.setText(user.getMetier());
        tvDateInscription.setText(user.getDateInscription());
        tvMail.setText(user.getAdresseMail());
        tvDescription.setText(user.getPresentation());

        String imageURL = user.getPhotoChemin();
        if( imageURL != null && !imageURL.isEmpty()) {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(imageURL, imgProfil);
        }

        int imageSexe = IMAGE_FEMALE;
        if(user.getSexe()) imageSexe = IMAGE_MALE;
        imgSexe.setImageResource(imageSexe);

    }
}
