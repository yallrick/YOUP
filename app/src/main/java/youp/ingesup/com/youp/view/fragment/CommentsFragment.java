package youp.ingesup.com.youp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.Message;
import youp.ingesup.com.youp.model.services.ForumService;
import youp.ingesup.com.youp.view.EventActivity;
import youp.ingesup.com.youp.view.adapter.CommentAdapter;

/**
 * Created by Damiano on 14/11/2014.
 */
public class CommentsFragment  extends Fragment {

    private OnFragmentInteractionListener mListener;

    private List<Message> comments;
    private ListView listView;
    private Integer eventID;
    private ForumService serviceForum;
    private ProgressBar loadComment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_comments, container, false);


        RestAdapter serviceForumBuilder = new RestAdapter.Builder().setEndpoint("http://forumyoup.apphb.com/").build();
        serviceForum = serviceForumBuilder.create(ForumService.class);

        /* Récupérer les informations de l'event : Indent + Appel API */
        eventID = ((EventActivity)getActivity()).eventID;

        final EditText etComments = (EditText)viewRoot.findViewById(R.id.etComments);
        loadComment = (ProgressBar)viewRoot.findViewById(R.id.loadComment);
        listView = (ListView) viewRoot.findViewById(R.id.listComments);
        Button btnSendComment = (Button)viewRoot.findViewById(R.id.btnSendComment);



        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                if(!Auth.isLoggedIn()){
                    Toast.makeText(getActivity(), "You have to be logged in.", Toast.LENGTH_LONG).show();
                    return;
                }
                /**/

                String idUser = String.valueOf(Auth.getInstance().getUser().getId());
                //String idUser = "7";

                serviceForum.sendMessage(String.valueOf(eventID), idUser, etComments.getText().toString(), "2014-11-20 12:35:29.123", new Callback<Boolean>() {
                    @Override
                    public void success(Boolean aBoolean, Response response) {
                        if(aBoolean)
                            loadComments();
                        else
                            Toast.makeText(getActivity(), "Comments are not allowed here. Sorry!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), "Fail to send your comment. Please, try again later.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Dialog de signalement et partage.
            }

        });


        loadComments();

        return viewRoot;
    }


    private void loadComments(){

        loadComment.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        serviceForum.getMessages(String.valueOf(eventID), new Callback<Message[]>() {
            @Override
            public void success(Message[] commentaires, Response response) {

                loadComment.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                comments = new ArrayList<Message>();
                Collections.addAll(comments, commentaires);

                CommentAdapter adapter = new CommentAdapter(getActivity(), commentaires);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if(getActivity() != null)
                    Toast.makeText(getActivity(), "Fail to load comments.", Toast.LENGTH_LONG).show();
            }
        });
    }



    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}

