package youp.ingesup.com.youp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.LinkedList;
import java.util.List;

import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.Evenement;

/**
 * Created by Vincent del Valle on 07/11/2014.
 */
public class EventAdapter extends ArrayAdapter<Evenement> {

    private int resource;
    private List<Evenement> evenements;

    public EventAdapter(Context context, int resource, List<Evenement> objects) {
        super(context, resource, objects);

        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resource, parent, false);
        }

        Evenement currentItem = evenements.get(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        TextView time = (TextView)convertView.findViewById(R.id.time);
        TextView duration = (TextView)convertView.findViewById(R.id.duration);
        TextView location = (TextView)convertView.findViewById(R.id.location);
        TextView category = (TextView)convertView.findViewById(R.id.category);
        TextView price = (TextView)convertView.findViewById(R.id.price);
        TextView title = (TextView)convertView.findViewById(R.id.title);

        ImageLoader imageLoader = ImageLoader.getInstance();


        /*/ TODO get image URL
        String imageURL = ?;
        imageLoader.displayImage(imageURL, imageView);
        /**/

        date.setText(currentItem.getDate());

        // TODO : get text lieu

        return convertView;
    }
}
