package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.view.HomeActivity;
import youp.ingesup.com.youp.view.adapter.EventAdapter;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountEventsFragment extends Fragment {

    private List<Evenement> events;
    private ListView listView;

    private TextView textViewNoResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_account_events, container, false);

        Integer profile_id = MainAccountFragment.profileID;
        if(profile_id == 0 && Auth.getInstance().getUser() != null)
            profile_id = Auth.getInstance().getUser().getId();

        listView = (ListView)root.findViewById(R.id.listEventProfile);
        textViewNoResult = (TextView) root.findViewById(R.id.tv_no_result);
        textViewNoResult.setText("Aucun évènement disponible.");

        RestAdapter serviceEventBuilder = new RestAdapter.Builder().setEndpoint("http://youp-evenementapi.azurewebsites.net/").build();
        EventService serviceEvent = serviceEventBuilder.create(EventService.class);
        serviceEvent.getEvents(profile_id.toString(),new Callback<List<Evenement>>() {
            @Override
            public void success(List<Evenement> evenements, Response response) {
                if(evenements == null || evenements.size() == 0){
                    textViewNoResult.setVisibility(View.VISIBLE);

                    listView.setVisibility(View.GONE);
                    return;
                }

                events = evenements;

                EventAdapter adapter = new EventAdapter(getActivity(), R.layout.item_event, events);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Activity  context = getActivity();

                if(context != null && error.getResponse() != null)
                    Toast.makeText(context, "Impossible de charger les events. " + error.getResponse().getStatus(), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "Impossible de charger les events. ", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try{

                    ((HomeActivity) getActivity()).goToFragment(MainEventFragment.newInstance(events.get(position).getEvenement_id()));
                }catch(Exception ex)
                {
                    Log.e("EventFragment - Envoi de l'ID vers EventActivity", ex.getMessage());
                }

            }

        });

        return root;
    }

}
