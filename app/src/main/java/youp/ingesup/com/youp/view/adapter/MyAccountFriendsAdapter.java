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

import retrofit.RestAdapter;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.DateTime;
import youp.ingesup.com.youp.model.bean.Evenement;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.bean.Message;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.EventService;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountFriendsAdapter extends ArrayAdapter<Friend> {

    private int resource;
    private List<Friend> friends;

    public MyAccountFriendsAdapter(Context context, int resource, List<Friend> objects) {
        super(context, resource, objects);

        this.resource = resource;
        this.friends = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //if(convertView == null){
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(resource, parent, false);
        //}

        final Friend currentItem = friends.get(position);

        ImageView imgFriend = (ImageView)convertView.findViewById(R.id.imgFriend);
        TextView tvPseudo = (TextView)convertView.findViewById(R.id.date);

        // image
        String imageURL = currentItem.getUrlPhoto();
        if( imageURL != null && !imageURL.isEmpty())
        {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(imageURL, imgFriend);
        }

        tvPseudo.setText(currentItem.getPseudo());

        return convertView;
    }
}
