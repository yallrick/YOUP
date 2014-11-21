package youp.ingesup.com.youp.view.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.view.HomeActivity;
import youp.ingesup.com.youp.view.adapter.MyAccountFriendsAdapter;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountFriendsFragment extends Fragment {

    private List<Friend> friends;
    private ListView listView;
    private UserService serviceUser;

    private TextView textViewNoResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_my_account_friends, container, false);

        Integer profile_id = MainAccountFragment.profileID;
        if(profile_id == 0 && Auth.getInstance().getUser() != null)
            profile_id = Auth.getInstance().getUser().getId();

        listView = (ListView)viewRoot.findViewById(R.id.listViewFriends);
        textViewNoResult = (TextView) viewRoot.findViewById(R.id.tv_no_result);
        textViewNoResult.setText("Vous n'avez aucun ami.");

        RestAdapter serviceUserBuilder = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
        serviceUser = serviceUserBuilder.create(UserService.class);

        serviceUser.getFriends(profile_id.toString(), new Callback<Friend[]>() {
            @Override
            public void success(Friend[] users, Response response) {

                if(users == null || users.length == 0) {
                    textViewNoResult.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    return;
                }

                friends = new ArrayList<Friend>();
                Collections.addAll(friends, users);
                MyAccountFriendsAdapter adapter = new MyAccountFriendsAdapter(getActivity(),R.layout.item_friend ,friends);
                listView.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(),  "Fail to get the friend's list. Please, try again later.", Toast.LENGTH_LONG).show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try{
                    ((HomeActivity)getActivity()).goToFragment(MainEventFragment.newInstance(friends.get(position).getId()), "detail");
                }catch(Exception ex)
                {
                    Log.e("EventFragment - Envoi de l'ID vers EventActivity", ex.getMessage());
                }

            }

        });

        return viewRoot;
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}
