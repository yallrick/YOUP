package youp.ingesup.com.youp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.bean.User;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountFriendsAdapter extends ArrayAdapter<Friend> {

    private static final int resource = R.layout.item_friend;

    public MyAccountFriendsAdapter(Context context, Friend[] objects) {
        super(context, resource , objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resource, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
        TextView textView = (TextView)convertView.findViewById(R.id.textView);

        Friend currentItem = getItem(position);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(currentItem.getUrlPhoto(), imageView);
        textView.setText(currentItem.getPseudo());

        return convertView;
    }
}
