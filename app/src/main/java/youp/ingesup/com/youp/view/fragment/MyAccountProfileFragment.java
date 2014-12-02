package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.DateTime;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.view.HomeActivity;
import youp.ingesup.com.youp.view.adapter.MyAccountFriendsAdapter;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountProfileFragment extends Fragment {

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
    private Button buttonBecomeFriend;

    private int IMAGE_MALE = R.drawable.male_icon;
    private int IMAGE_FEMALE = R.drawable.female_icon;

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

        /*** Récupération éléments écran ***/
        tvPseudo = (TextView) root.findViewById(R.id.tvPseudoProfile);
        tvNom = (TextView) root.findViewById(R.id.tvNomProfile);
        tvPrenom = (TextView) root.findViewById(R.id.tvPrenomProfile);
        imgSexe = (ImageView) root.findViewById(R.id.imgGender);
        tvDateNaissance = (TextView) root.findViewById(R.id.tvDateNaissance);
        tvVille = (TextView) root.findViewById(R.id.tvCity);
        tvMetier = (TextView) root.findViewById(R.id.tvMetier);
        tvDateInscription  = (TextView) root.findViewById(R.id.tvDateInscription);
        tvMail = (TextView) root.findViewById(R.id.tvEmailProfile);
        tvDescription = (TextView) root.findViewById(R.id.tvPresentationProfile);
        imgProfil = (ImageView) root.findViewById(R.id.imgProfile);
        buttonBecomeFriend = (Button) root.findViewById(R.id.btDevenirAmi);

        RestAdapter serviceUserBuilder = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
        userService = serviceUserBuilder.create(UserService.class);

        userService.getUser(profile_id.toString(),new Callback<User>(){

            @Override
            public void success(User profile, Response response) {
                user = profile;
                ChargementDonnees();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Fail to get the profile's information. Please, try again later.", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }

    private void ChargementDonnees()
    {
        tvPseudo.setText(user.getPseudo());
        tvNom.setText(user.getNom());
        tvPrenom.setText(user.getPrenom());

        DateTime dateTime = new DateTime(user.getDateNaissance());
        tvDateNaissance.setText(dateTime.getDay() + "/" + dateTime.getMonth() + "/" + dateTime.getYear());

        tvVille.setText(user.getVille());
        tvMetier.setText(user.getMetier());

        String dateInsc = user.getDateInscription().substring(0,user.getDateInscription().length() - 4);
        DateTime dt = new DateTime(dateInsc);
        tvDateInscription.setText(dt.getDay() + "/" + dt.getMonth() + "/" + dt.getYear());

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


        if(Auth.isLoggedIn()) {
            buttonBecomeFriend.setVisibility(View.VISIBLE);
            if (String.valueOf(Auth.getInstance().getUser().getId()).equals(String.valueOf(MainAccountFragment.profileID)))
                buttonBecomeFriend.setVisibility(View.GONE);
            else {
                final Integer profile_id = Auth.getInstance().getUser().getId();
                userService.getFriends(profile_id.toString(), new Callback<Friend[]>() {
                    @Override
                    public void success(Friend[] users, Response response) {
                        if (users == null || users.length == 0) {
                            buttonBecomeFriend.setVisibility(View.GONE);
                        } else {
                            List<Friend> friends = new ArrayList<Friend>();
                            Collections.addAll(friends, users);

                            for (Friend f : friends) {
                                if (f.getId() == profile_id)
                                    buttonBecomeFriend.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        buttonBecomeFriend.setVisibility(View.GONE);
                    }
                });
            }

            buttonBecomeFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DevenirAmi();
                }
            });
        }else{
            buttonBecomeFriend.setVisibility(View.GONE);
        }




    }

    private void DevenirAmi()
    {
        if (Auth.getInstance() != null)
        {
            RestAdapter serviceUserBuilder = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
            userService = serviceUserBuilder.create(UserService.class);

            /*** Envoi une requpete d'ami ***/
            userService.sendFriendRequest(Auth.getInstance().getUser().getId().toString(), MainAccountFragment.profileID.toString() ,new Callback<Boolean>() {
                @Override
                public void success(Boolean aBoolean, Response response) {
                    Toast.makeText(getActivity(), "Friend request sended.", Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(),  "Fail to send the friend request. Please, try again later.", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            Toast.makeText(getActivity(), "You have to be logged in.", Toast.LENGTH_LONG).show();

            ((HomeActivity) getActivity()).goToFragment(MainLoginFragment.newInstance(false), "login");
        }
    }
}
