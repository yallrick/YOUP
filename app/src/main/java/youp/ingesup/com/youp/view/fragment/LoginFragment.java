package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginFragment extends Fragment{
    private OnFragmentInteractionListener mListener;

    private Button btLogin;
    private Button btLostPassword;

    private EditText etUsername;
    private EditText etPassword;

    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void Login()
    {
        if(checkValidation())
        {
            /* TODO: Envoi des informations à l'API et étude du retour */

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
            UserService service = restAdapter.create(UserService.class);
            service.login(username, password, new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    Auth auth = Auth.getInstance(user, user.getToken());
                    Toast.makeText(getActivity(), "Connexion réussie.", Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Connexion échouée.", Toast.LENGTH_LONG).show();
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
