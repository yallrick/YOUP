package youp.ingesup.com.youp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.Auth;
import youp.ingesup.com.youp.model.bean.Friend;
import youp.ingesup.com.youp.model.bean.User;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.view.adapter.MyAccountFriendsAdapter;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountFriendsFragment extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_account_friends, container, false);

        listView = (ListView)root.findViewById(R.id.listView);

        updateFragment();


        return root;
    }


    private void updateFragment(){

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://aspmoduleprofil.azurewebsites.net/").build();
        UserService service = restAdapter.create(UserService.class);

        int userId = Auth.getInstance().getUser().getId();
        service.getFriends(String.valueOf(userId), new Callback<Friend[]>() {
            @Override
            public void success(Friend[] users, Response response) {
                MyAccountFriendsAdapter adapter = new MyAccountFriendsAdapter(getActivity(), users);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Impossible de récupérer la liste de amis", Toast.LENGTH_LONG).show();
            }
        });

    }
}
