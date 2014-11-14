package youp.ingesup.com.youp.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.services.EventService;
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

            }
        });

        listView = (ListView)viewRoot.findViewById(R.id.list);

        return viewRoot;
    }
}
