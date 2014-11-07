package youp.ingesup.com.youp;

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
import android.widget.TextView;

import java.util.Calendar;

public class SignUpFragment extends Fragment{

    private OnFragmentInteractionListener mListener;

    private int pYear;
    private int pMonth;
    private int pDay;

    private TextView tVDateNaissance;

    public SignUpFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        Button btDatePicker = (Button)root.findViewById(R.id.btChooseDateSignUp);
        tVDateNaissance = (TextView)root.findViewById(R.id.tVDateNaissanceSignUp);

        btDatePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog();
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

}
