package youp.ingesup.com.youp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

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

        //if(convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resource, parent, false);
        //}

        final Evenement currentItem = evenements.get(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        final TextView locationView = (TextView)convertView.findViewById(R.id.location);
        final TextView categoryView = (TextView)convertView.findViewById(R.id.category);
        TextView price = (TextView)convertView.findViewById(R.id.price);
        TextView title = (TextView)convertView.findViewById(R.id.title);


        // image
        /*
        String imageURL = currentItem.getImageUrl();
        if( imageURL != null && !imageURL.isEmpty()) {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(imageURL, imageView);
        }
        else
        {
            imageView.setVisibility(View.GONE);
        }
*/
        // date
        DateTime dateTime = new DateTime(currentItem.getDateEvenement());
        date.setText(dateTime.getDateInFrench());

        // prix
        price.setText(currentItem.getPrix() + " €");

        // titre
        title.setText(currentItem.getTitreEvenement());

        // lieu (pays)
        locationView.setText(currentItem.getAdresse().getVille());

        // category
        categoryView.setText(currentItem.getCategorie_Libelle());

        return convertView;
    }
}
