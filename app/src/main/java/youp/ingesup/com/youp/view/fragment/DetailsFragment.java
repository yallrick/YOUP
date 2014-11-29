package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.DateTime;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.view.HomeActivity;

/**
 * Created by Damiano on 14/11/2014.
 */
public class DetailsFragment extends Fragment {
    private SignUpFragment.OnFragmentInteractionListener mListener;

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvDate;
    private TextView tvPrice;
    private TextView tvLocation;
    private TextView tvCategorie;
    private ImageView img;
    private ImageView imgCategorie;

    private Button btOrganizer;

    private Integer eventID;
    private Evenement evenement;

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
        imgCategorie = (ImageView) root.findViewById(R.id.imgCategorie);
        btOrganizer = (Button) root.findViewById(R.id.btOrganizerDetails);
        Button btSubscribe = (Button) root.findViewById(R.id.btSubscribeDetails);
        Button btShare = (Button) root.findViewById(R.id.btShareDetails);

        /* Gestion des événements */
        btOrganizer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OpenOrganizer();
            }
        });
        btSubscribe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Subscription();
            }
        });

        btShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if(getActivity() == null || evenement == null)
                    return;

                try {
                    String adUrl = "";

                    adUrl += "<h1>"+evenement.getTitreEvenement()+"</h1>";
                    adUrl += "<p>"+ evenement.getDescriptionEvenement() +"</p>";
                    adUrl += "<p><a href=\"#\">See more</a></p>";

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Event YOUP");
                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(adUrl));

                    getActivity().startActivity(Intent.createChooser(intent, "Email :"));
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "Fail to share by email.", Toast.LENGTH_LONG).show();
                }catch (Exception ignored){}

            }
        });

        /* Récupérer les informations de l'event : Indent + Appel API */
        this.eventID = MainEventFragment.eventID;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://youp-evenementapi.azurewebsites.net/").build();
        EventService service = restAdapter.create(EventService.class);
        service.getEvent(eventID.toString(), new Callback<Evenement>() {
            @Override
            public void success(Evenement event, Response response) {

                if(event == null)
                    return;

                evenement = event;

                tvTitle.setText(evenement.getTitreEvenement());

                DateTime dateTime = new DateTime(evenement.getDateEvenement());
                tvDate.setText(dateTime.getDateInFrench());


                if(evenement.getPrix() != null)
                    tvPrice.setText(evenement.getPrix() + " €");
                else
                    tvPrice.setText("- €");

                if(evenement.getAdresse() != null)
                    tvLocation.setText(evenement.getAdresse().getAdresse());
                else
                    tvLocation.setText("-");

                ChargerCategorie();


                if(evenement.getImageUrl() != null && !evenement.getImageUrl().isEmpty()) {
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    imageLoader.displayImage(evenement.getImageUrl(), img);
                }else{

                    if(evenement.getImageUrl() != null && !evenement.getImageUrl().isEmpty()) {
                        ImageLoader imageLoader = ImageLoader.getInstance();
                        imageLoader.displayImage(evenement.getImageUrl(), img);
                    }
                }


                tvDescription.setText(evenement.getDescriptionEvenement());
                btOrganizer.setText(evenement.getOrganisateurPseudo());

            }

            @Override
            public void failure(RetrofitError error) {
                Activity context = getActivity();

                if(context != null)
                    Toast.makeText(context, "Fail to find event's details.", Toast.LENGTH_LONG).show();
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

    private void Subscription()
    {

        if(!Auth.isLoggedIn()){
            Toast.makeText(getActivity(), "You have to be logged in.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(this.evenement == null || this.evenement.getEvenement_id() == null || Auth.getInstance() == null || Auth.getInstance().getUser() == null || Auth.getInstance().getUser().getId() == null)
            return;

        if(Auth.isLoggedIn()) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
            EventService service = restAdapter.create(EventService.class);
            service.joinEvent(this.evenement.getEvenement_id().toString(), Auth.getInstance().getUser().getId().toString(), new Callback<Boolean>() {
                @Override
                public void success(Boolean aBoolean, Response response) {
                    if(getActivity() == null)
                        return;

                    if(aBoolean){
                        Toast.makeText(getActivity(), "Registration confirmed.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Registration failed.", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                    if(getActivity() != null)
                        Toast.makeText(getActivity(), "Registration failed.", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(getActivity(), "You have to be logged in.", Toast.LENGTH_LONG).show();
        }
    }

    private void OpenOrganizer()
    {
        if(evenement == null){

            return;
        }

        // ENVOYER VERS INTENT DE PROFIL
        Integer id_organizer = evenement.getOrganisateurId();

        try{
            ((HomeActivity) getActivity()).goToFragment(MainAccountFragment.newInstance(id_organizer), "account");
        }catch(Exception ex)
        {
            Log.e("EventFragment - Envoi de l'ID vers EventActivity", ex.getMessage());
        }

    }

    private void ChargerCategorie()
    {
        String categorie = evenement.getCategorie_Libelle();

        if(categorie != null && !categorie.isEmpty())
            tvCategorie.setText(categorie);
        else
            tvCategorie.setText("-");

        if(categorie.equals("Sport"))
            imgCategorie.setImageResource(R.drawable.sport);
        else if (categorie.equals("Musée"))
            imgCategorie.setImageResource(R.drawable.museum);
        else if (categorie.equals("Marche a pied"))
            imgCategorie.setImageResource(R.drawable.walking);
        else if (categorie.equals("Apéro entre ami"))
            imgCategorie.setImageResource(R.drawable.aperitif);
        else if (categorie.equals("Anniversaire"))
            imgCategorie.setImageResource(R.drawable.birsthday);

    }

}
