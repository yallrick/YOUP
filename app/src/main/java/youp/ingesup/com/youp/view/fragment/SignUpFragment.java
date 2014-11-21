package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.tool.FieldValidator;

public class SignUpFragment extends Fragment{

    private OnFragmentInteractionListener mListener;

    private int pYear;
    private int pMonth;
    private int pDay;

    private TextView tVDateNaissance;
    private EditText etPseudo;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etMail;
    private EditText etLastName;
    private EditText etFirstName;
    private EditText etCity;
    private EditText etZIPCode;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    public static SignUpFragment newInstance(){
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_sign_up, container, false);


        /* Récupération élément écran */
        Button btDatePicker = (Button)root.findViewById(R.id.btChooseDateSignUp);
        tVDateNaissance = (TextView)root.findViewById(R.id.tVDateNaissanceSignUp);
        etCity = (EditText)root.findViewById(R.id.etCitySignUp);
        etZIPCode = (EditText)root.findViewById(R.id.etZIPSignUp);
        etConfirmPassword = (EditText)root.findViewById(R.id.etPasswordConfirmSignUp);
        etPseudo = (EditText)root.findViewById(R.id.etUsernameSignUp);
        etPassword = (EditText)root.findViewById(R.id.etPasswordSignUp);
        etFirstName = (EditText)root.findViewById(R.id.etFirstNameSignUp);
        etLastName = (EditText)root.findViewById(R.id.etLastNameSignUp);
        etMail = (EditText)root.findViewById(R.id.etMailSignUp);
        rbFemale = (RadioButton)root.findViewById(R.id.rBFemaleSignUp);
        rbMale = (RadioButton)root.findViewById(R.id.rBMaleSignUp);
        Button btSignUp = (Button)root.findViewById(R.id.bt_SignUp);


        /* Gestion des événements */
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                SignUp();
              }
        });

        /** Get the current date **/
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        /** Display the current date in the TextView */
        updateDisplay();

        return root;

    }

    private void showDialog() {
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this.getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        pDay = dayOfMonth - 1;
                        pMonth = monthOfYear + 1;
                        pYear = year;

                        updateDisplay();
                    }
                }, pYear, pMonth - 1, pDay + 1);
        dpd.show();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    /** Updates the date in the TextView */
    private void updateDisplay() {
        tVDateNaissance.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pDay + 1).append("/")
                        .append(pMonth).append("/")
                        .append(pYear).append(" "));
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!FieldValidator.hasText(etPseudo)) ret = false;
        if (!FieldValidator.hasText(etPassword)) ret = false;
        if (!FieldValidator.hasText(etConfirmPassword)) ret = false;
        if (!FieldValidator.isEmailAddress(etMail, true)) ret = false;

        if(ret == true)
        {
            if(!FieldValidator.isConfirmPassword(etPassword,etConfirmPassword)) ret = false;
        }

        return ret;
    }


    private void SignUp()
    {
        if(checkValidation())
        {
            final String username = etPseudo.getText().toString();
            final String password = etPassword.getText().toString();
            String mail = etMail.getText().toString();
            String lastName = etLastName.getText().toString();
            String firstName = etFirstName.getText().toString();
            String birthday = tVDateNaissance.getText().toString();
            String city = etCity.getText().toString();
            String zipcode = etZIPCode.getText().toString();
            boolean gender = true;

            if(rbFemale.isChecked())
                gender = false;

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
            final UserService service = restAdapter.create(UserService.class);
            service.createUser(username, password, firstName, lastName, gender, mail, birthday, city, zipcode, new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    service.login(username, password, "Android", new Callback<User>() {
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

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Création échouée.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
