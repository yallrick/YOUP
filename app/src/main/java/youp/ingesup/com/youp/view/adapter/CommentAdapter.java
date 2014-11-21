package youp.ingesup.com.youp.view.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
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
import youp.ingesup.com.youp.model.bean.Message;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.EventService;
import youp.ingesup.com.youp.model.services.ForumService;
import youp.ingesup.com.youp.model.services.UserService;

/**
 * Created by Damiano on 20/11/2014.
 */

public class CommentAdapter extends ArrayAdapter<Message>
{

    private static final int resource = R.layout.item_comment;


    private HashMap<Integer, String> mapNames;
    private UserService userService;

    public CommentAdapter(Context context, Message[] objects) {
        super(context, resource, objects);
        mapNames = new HashMap<Integer, String>();

        RestAdapter serviceForumBuilder = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
        userService = serviceForumBuilder.create(UserService.class);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(resource, parent, false);


        final Message currentItem = getItem(position);

        final TextView tvWriterComment = (TextView)convertView.findViewById(R.id.tvWriterComment);
        TextView tvDate = (TextView)convertView.findViewById(R.id.tvDateComment);
        TextView tvComment = (TextView)convertView.findViewById(R.id.tvComment);



        // Chargement des données
        if(tvWriterComment != null)
            tvWriterComment.setText("#" + String.valueOf(currentItem.getUserId()));

        if( !mapNames.containsKey(currentItem.getUserId()) ){
            mapNames.put(currentItem.getUserId(), "");
            userService.getUser(String.valueOf(currentItem.getUserId()), new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    mapNames.put(currentItem.getUserId(), user.getPseudo());
                    if(tvWriterComment != null)
                        tvWriterComment.setText(mapNames.get(currentItem.getUserId()));
                }

                @Override
                public void failure(RetrofitError error) {
                    mapNames.put(currentItem.getUserId(), null);
                }
            });
        }


        tvComment.setText(Html.fromHtml(currentItem.getContent()));

        DateTime dateTime = new DateTime(currentItem.getDatePost());
        tvDate.setText(dateTime.getDateInFrench());


        return convertView;
    }
}
