package youp.ingesup.com.youp.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import youp.ingesup.com.youp.R;

/**
 * Created by Damiano on 14/11/2014.
 */
public class CommentsFragment  extends Fragment {

    private SignUpFragment.OnFragmentInteractionListener mListener;

    //private List<Comment> comments;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_comments, container, false);

        /* Récupérer les informations de l'event : Indent + Appel API */
        Integer eventID = MainEventFragment.eventID;

/*
        // TODO: Changer la route.
        RestAdapter serviceForumBuilder = new RestAdapter.Builder().setEndpoint("http://youp-evenementapi.azurewebsites.net/").build();
        ForumService serviceForum = serviceForumBuilder.create(ForumService.class);
        serviceForum.getComments(eventID.ToString(),new Callback<List<Comment>>() {
            @Override
            public void success(List<Comment> commentaires, Response response) {
                comments = commentaires;

                ForumAdapter adapter = new ForumAdapter(getActivity(), R.layout.item_comment, events);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Impossible de charger les commentaires.", Toast.LENGTH_LONG).show();
            }
        });
*//*
        listView = (ListView) viewRoot.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Dialog de signalement et partage.
            }

        });
*/


        return viewRoot;
    }
}

