package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.view.EventActivity;
import youp.ingesup.com.youp.view.adapter.EventAdapter;

/**
 * Created by Vincent del Valle on 07/11/2014.
 */
public class EventFragment extends Fragment {

    private List<Evenement> events;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate( R.layout.fragment_event, container, false);

        // http://www.mocky.io/v2/546c52f8a112329207713535
        // http://youp-evenementapi.azurewebsites.net/
        RestAdapter serviceEventBuilder = new RestAdapter.Builder().setEndpoint("http://youp-evenementapi.azurewebsites.net/").build();
        EventService serviceEvent = serviceEventBuilder.create(EventService.class);
        serviceEvent.getEvents(new Callback<List<Evenement>>() {
            @Override
            public void success(List<Evenement> evenements, Response response) {
                events = evenements;

                EventAdapter adapter = new EventAdapter(getActivity(), R.layout.item_event, events);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Activity  context = getActivity();

                if(context != null)
                    Toast.makeText(context, "Impossible de charger les events. " + error.getResponse().getStatus(), Toast.LENGTH_LONG).show();
            }
        });

        listView = (ListView)viewRoot.findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try{
                    Intent intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra(EventActivity.PARAM_ID_EVENT, events.get(position).getEvenement_id());
                    //intent.putExtra(EventActivity.PARAM_ID_PROFILE, events.get(position).());
                    startActivity(intent);
                }catch(Exception ex)
                {
                    Log.e("EventFragment - Envoi de l'ID vers EventActivity", ex.getMessage());
                }

            }

        });

        return viewRoot;
    }
}
