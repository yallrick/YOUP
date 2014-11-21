package youp.ingesup.com.youp.view;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.adapter.MyAccountAdapter;
import youp.ingesup.com.youp.adapter.TabsEventAdapter;
import youp.ingesup.com.youp.view.fragment.CommentsFragment;
import youp.ingesup.com.youp.view.fragment.DetailsFragment;
import youp.ingesup.com.youp.view.fragment.MyAccountEventsFragment;
import youp.ingesup.com.youp.view.fragment.MyAccountFriendsFragment;
import youp.ingesup.com.youp.view.fragment.MyAccountProfileFragment;

import static android.app.ActionBar.TabListener;


/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountActivity extends FragmentActivity implements ActionBar.TabListener, MyAccountFriendsFragment.OnFragmentInteractionListener, MyAccountProfileFragment.OnFragmentInteractionListener, MyAccountEventsFragment.OnFragmentInteractionListener {
    private ViewPager viewPager;
    private MyAccountAdapter mAdapter;
    private ActionBar actionBar;

    public static final String PARAM_ID_PROFILE = "PARAM_ID_PROFILE";

    public Integer eventID;
    public Integer profileID;

    // Tab titles
    private String[] tabs = { "Friends", "Events", "Profile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.vpMyAccount);
        actionBar = getActionBar();
        mAdapter = new MyAccountAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Récupération de l'ID de l'event
        profileID = getIntent().getExtras().getInt(PARAM_ID_PROFILE);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
