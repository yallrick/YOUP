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


    public MyAccountAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new MyAccountFriendsFragment();
            case 1:
                return new MyAccountEventsFragment();
            case 2:
                return new MyAccountProfileFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
