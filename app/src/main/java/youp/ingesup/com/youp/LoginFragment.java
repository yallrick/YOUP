package youp.ingesup.com.youp;

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
        if(ControleSaisie())
        {
            /* TODO: Envoi des informations à l'API et étude du retour */
        }
    }

    private void LostPassword()
    {
        if(etUsername.getText().toString() == "")
        {
            Toast.makeText(getActivity(),"Attention : le champs Username est obligatoire!",Toast.LENGTH_LONG ).show();
            return;
        }

        /* TODO: Gérer l'oubli de mot de passe */

    }

    private boolean ControleSaisie()
    {
        boolean res = true;
        String msg = "Problème(s) de saisie:";

        if(etPassword.getText().toString() == "")
        {
            res = false;
            msg += "\n - Le champs Password est obligatoire";
        }

        if(etUsername.getText().toString() == "")
        {
            res = false;
            msg += "\n - Le champs Username est obligatoire";
        }

        if(!res)
        {
            Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG ).show();
        }

        return res;
    }

}
