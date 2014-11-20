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
 * Created by Damiano on 20/11/2014.
 */

public class CommentAdapter //extends ArrayAdapter<Comment>
{

    private int resource;
    //private List<Comment> commentaires;

    public CommentAdapter(Context context, int resource, List<Evenement> objects) {
        //super(context, resource, objects);

        this.resource = resource;
        //this.commentaires = objects;
    }
/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( true || convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resource, parent, false);
        }

      final Comment currentItem = commentaires.get(position);

        TextView tvWriterComment = (TextView)convertView.findViewById(R.id.tvWriterComment);
        TextView tvDate = (TextView)convertView.findViewById(R.id.tvDateComment);
        TextView tvComment = (TextView)convertView.findViewById(R.id.tvComment);

        // Chargement des données
        tvWriterComment.setText(currentItem.getWriter());
        tvComment.setText(currentItem.getComment());

        DateTime dateTime = new DateTime(currentItem.getDate());
        tvDate.setText(dateTime.getDateInFrench());


        return convertView;
    }
 */
}
