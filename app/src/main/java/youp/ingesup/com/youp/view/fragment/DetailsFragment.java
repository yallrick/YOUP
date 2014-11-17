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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.view.EventActivity;

/**
 * Created by Damiano on 14/11/2014.
 */
public class DetailsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvDate;
    private TextView tvPrice;
    private TextView tvLocation;
    private TextView tvCategorie;
    private ImageView img;

    private Button btOrganizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_details, container, false);

        /* Récupération des éléments de l'écran */
        tvTitle = (TextView) root.findViewById(R.id.tvTitleEventDetails);
        tvDescription = (TextView) root.findViewById(R.id.tvDescriptionDetails);
        tvDate = (TextView) root.findViewById(R.id.tvDateDetails);
        tvPrice = (TextView) root.findViewById(R.id.tvPriceDetails);
        tvLocation = (TextView) root.findViewById(R.id.tvLocationDetails);
        tvCategorie = (TextView) root.findViewById(R.id.tvCategoryDetails);
        img = (ImageView) root.findViewById(R.id.imgDetails);
        btOrganizer = (Button) root.findViewById(R.id.btOrganizerDetails);
        Button btSubscribe = (Button) root.findViewById(R.id.btSubscribeDetails);
        Button btShare = (Button) root.findViewById(R.id.btShareDetails);

        /* Gestion des événements */
        btOrganizer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /* TODO: Renvoyer vers le profil de l'organisateur */
            }
        });
        btSubscribe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /* TODO: Appeler la route de subscription */
            }
        });
        btShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /* TODO: Partager l'événement */
            }
        });

        /* Récupérer les informations de l'event : Indent + Appel API */
        Integer eventID = ((EventActivity)getActivity()).eventID;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
        EventService service = restAdapter.create(EventService.class);
        service.getEvent(eventID.toString(), new Callback<Evenement>() {
            @Override
            public void success(Evenement evenement, Response response) {
                tvTitle.setText(evenement.getTitre());
                /* TODO: Remplir les autres champs */
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Connexion échouée.", Toast.LENGTH_LONG).show();
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
        public void onFragmentInteraction(Uri uri);
    }
}