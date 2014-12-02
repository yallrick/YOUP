package youp.ingesup.com.youp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import youp.ingesup.com.youp.view.fragment.*;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountAdapter extends FragmentStatePagerAdapter {

    private MyAccountFriendsFragment myAccountFriendsFragment;
    private MyAccountEventsFragment myAccountEventsFragment;
    private MyAccountProfileFragment myAccountProfileFragment;

    public MyAccountAdapter(FragmentManager fm) {
        super(fm);

        myAccountFriendsFragment = new MyAccountFriendsFragment();
        myAccountEventsFragment = new MyAccountEventsFragment();
        myAccountProfileFragment = new MyAccountProfileFragment();
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return myAccountProfileFragment;
            case 1:
                return myAccountEventsFragment;
            case 2:
                return myAccountFriendsFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
