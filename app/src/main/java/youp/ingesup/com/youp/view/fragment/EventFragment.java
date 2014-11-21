package youp.ingesup.com.youp.view.fragment;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.view.HomeActivity;
import youp.ingesup.com.youp.view.adapter.EventAdapter;

/**
 * Created by Vincent del Valle on 07/11/2014.
 */
public class EventFragment extends Fragment {

    private List<Evenement> events;
    private ListView listView;
    private ProgressBar loadEvent;
    private ProgressBar bottomView;
    private String maxId;
    private EventAdapter adapter;
    private int mLastItem;


    private TextView textViewNoResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate( R.layout.fragment_event, container, false);

        // http://www.mocky.io/v2/546c52f8a112329207713535
        // http://youp-evenementapi.azurewebsites.net/

        loadEvent = (ProgressBar)viewRoot.findViewById(R.id.loadEvent);
        listView = (ListView)viewRoot.findViewById(R.id.list);

        textViewNoResult = (TextView) viewRoot.findViewById(R.id.tv_no_result);
        textViewNoResult.setText("No event available.");

        ActionBar actionBar = getActivity().getActionBar();

        if(actionBar != null) {
            actionBar.removeAllTabs();
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }


        bottomView = new ProgressBar(getActivity());
        listView.addFooterView(bottomView);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) { }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(visibleItemCount == 0 || totalItemCount == 0)
                    return;

                int lastItem = firstVisibleItem + visibleItemCount;

                boolean footerIsLoadingFooter = (view.getChildAt(view.getChildCount() - listView.getFooterViewsCount()) == bottomView);

                if(lastItem == totalItemCount && footerIsLoadingFooter && lastItem != mLastItem) {

                    loadEvents(maxId);
                }

                mLastItem = lastItem;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try{
                    ((HomeActivity) getActivity()).goToFragment(MainEventFragment.newInstance(events.get(position).getEvenement_id()), "detail");
                }catch(Exception ex)
                {
                    Log.e("EventFragment", "Fail to open new Fragment (Envoi de l'ID vers EventActivity)");
                }

            }

        });

        loadEvents(null);

        return viewRoot;
    }

    public void loadEvents(String maxIdParam){


        RestAdapter serviceEventBuilder = new RestAdapter.Builder().setEndpoint("http://youp-evenementapi.azurewebsites.net/").build();
        EventService serviceEvent = serviceEventBuilder.create(EventService.class);


        if(maxIdParam == null || maxIdParam.isEmpty()) {


            loadEvent.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);

            //////////////////////////////////////////////
            ////////////// REFRESH LOADING ///////////////
            //////////////////////////////////////////////


            serviceEvent.getEvents(new Callback<List<Evenement>>() {
                @Override
                public void success(List<Evenement> evenements, Response response) {
                    if(evenements == null || evenements.size() ==0){

                        textViewNoResult.setVisibility(View.VISIBLE);
                        loadEvent.setVisibility(View.GONE);
                        listView.setVisibility(View.GONE);
                        return;

                    }

                    loadEvent.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                    events = evenements;

                    if(evenements != null && evenements.size() >= 1)
                        maxId = String.valueOf(evenements.get(evenements.size() - 1).getEvenement_id());

                    if(getActivity() != null && events != null) {
                        adapter = new EventAdapter(getActivity(), R.layout.item_event, events);
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Activity context = getActivity();

                    if (context != null) {
                        loadEvent.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "Fail to refresh. " + error.getResponse().getStatus(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{

            ////////////////////////////////////////////
            ////////////// BOTTOM LOADING //////////////
            ////////////////////////////////////////////


            serviceEvent.getNextEvents(maxIdParam, new Callback<List<Evenement>>() {
                @Override
                public void success(List<Evenement> evenements, Response response) {

                    loadEvent.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                    if(evenements != null && evenements.size() > 0) {
                        events.addAll(evenements);
                        maxId = String.valueOf(evenements.get(evenements.size() - 1).getEvenement_id());

                        adapter.notifyDataSetChanged();
                    }else{
                        bottomView.setVisibility(View.GONE);
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    Activity context = getActivity();

                    if (context != null) {
                        loadEvent.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "Fail to load next events. " + error.getResponse().getStatus(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}
