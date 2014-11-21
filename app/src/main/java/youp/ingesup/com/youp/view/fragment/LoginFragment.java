package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

import javax.xml.datatype.Duration;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.tool.FieldValidator;
import youp.ingesup.com.youp.view.HomeActivity;

public class LoginFragment extends Fragment{
    private SignUpFragment.OnFragmentInteractionListener mListener;

    private Button btLogin;
    private Button btLostPassword;

    private EditText etUsername;
    private EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        /* Récupération des éléments de la vue */
        btLogin = (Button)root.findViewById(R.id.bt_Login);
        btLostPassword = (Button) root.findViewById(R.id.btLostPassword);
        etPassword = (EditText) root.findViewById(R.id.etPassword);
        etUsername = (EditText) root.findViewById(R.id.etUsername);

        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Login();
            }
        });
        btLostPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LostPassword();
            }
        });

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SignUpFragment.OnFragmentInteractionListener) activity;
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

    private void Login()
    {
        if(checkValidation())
        {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
            UserService service = restAdapter.create(UserService.class);
            service.login(username, password, "Android", new Callback<User>() {
                @Override
                public void success(User user, Response response) {

                    Auth auth = Auth.getInstance(user, user.getToken());

                    Toast.makeText(getActivity(), "Vous êtes maintenant connecté.", Toast.LENGTH_LONG).show();


                    Intent refresh = new Intent(getActivity(), HomeActivity.class);
                    getActivity().startActivity(refresh);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Connexion impossible. Code " + error.getResponse().getStatus(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private void LostPassword()
    {
        if(etUsername.getText().toString().equals(""))
        {
            Toast.makeText(getActivity(),"Attention : le champs Username est obligatoire!",Toast.LENGTH_LONG ).show();
            return;
        }

        /* TODO: Gérer l'oubli de mot de passe */

    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!FieldValidator.hasText(etUsername)) ret = false;
        if (!FieldValidator.hasText(etPassword)) ret = false;

        return ret;
    }

}
