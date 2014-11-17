package youp.ingesup.com.youp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import youp.ingesup.com.youp.view.fragment.CommentsFragment;
import youp.ingesup.com.youp.view.fragment.DetailsFragment;

/**
 * Created by Damiano on 14/11/2014.
 */
public class TabsEventAdapter extends FragmentPagerAdapter {
    /**
     * Created by Damiano on 31/10/2014.
     */
    public TabsEventAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Login fragment activity
                return new DetailsFragment();
            case 1:
                // SignUp fragment activity
                return new CommentsFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
