package youp.ingesup.com.youp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.Categorie;
import youp.ingesup.com.youp.model.bean.DateTime;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.bean.Location;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.view.EventActivity;

/**
 * Created by Vincent del Valle on 07/11/2014.
 */
public class EventAdapter extends ArrayAdapter<Evenement> {

    private int resource;
    private List<Evenement> evenements;
    private EventService serviceEvent;

    public EventAdapter(Context context, int resource, List<Evenement> objects) {
        super(context, resource, objects);

        this.resource = resource;
        this.evenements = objects;
        RestAdapter serviceEventBuilder = new RestAdapter.Builder().setEndpoint("http://youp-evenementapi.azurewebsites.net/").build();
        serviceEvent = serviceEventBuilder.create(EventService.class);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( true || convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resource, parent, false);
        }

        final Evenement currentItem = evenements.get(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        final TextView locationView = (TextView)convertView.findViewById(R.id.location);
        final TextView categoryView = (TextView)convertView.findViewById(R.id.category);
        TextView price = (TextView)convertView.findViewById(R.id.price);
        TextView title = (TextView)convertView.findViewById(R.id.title);

        ImageLoader imageLoader = ImageLoader.getInstance();


        /*/ TODO get image URL
        String imageURL = ?;
        imageLoader.displayImage(imageURL, imageView);
        /**/

        // récupération du lieu



        DateTime dateTime = new DateTime(currentItem.getDate());
        date.setText(dateTime.getDateInFrench());


        price.setText(currentItem.getPrix() + " €");
        title.setText(currentItem.getTitre());

        serviceEvent.getLocation(String.valueOf(currentItem.getLieuId()), new Callback<Location>() {
            @Override
            public void success(Location location, Response response) {
                locationView.setText("empty");
            }

            @Override
            public void failure(RetrofitError error) {
                locationView.setText("#fail ("+currentItem.getLieuId()+")");
            }
        });
        locationView.setText("Loading...");


        serviceEvent.getCategories(String.valueOf(currentItem.getCategorieId()), new Callback<Categorie>() {
            @Override
            public void success(Categorie categorie, Response response) {
                categoryView.setText(categorie.getLabel());
            }

            @Override
            public void failure(RetrofitError error) {
                categoryView.setText("#fail " + error.getResponse().getStatus());
            }
        });
        categoryView.setText("Loading...");

        return convertView;
    }
}
