package youp.ingesup.com.youp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import youp.ingesup.com.youp.view.fragment.SignUpFragment;
import youp.ingesup.com.youp.view.fragment.LoginFragment;

public class TabsLoginSignUpAdapter extends FragmentPagerAdapter {
    /**
     * Created by Damiano on 31/10/2014.
     */
    public TabsLoginSignUpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Login fragment activity
                return new LoginFragment();
            case 1:
                // SignUp fragment activity
                return new SignUpFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}