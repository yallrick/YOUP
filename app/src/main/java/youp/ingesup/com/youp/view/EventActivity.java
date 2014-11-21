package youp.ingesup.com.youp.view;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.adapter.TabsEventAdapter;
import youp.ingesup.com.youp.view.fragment.CommentsFragment;
import youp.ingesup.com.youp.view.fragment.DetailsFragment;

import static android.app.ActionBar.TabListener;

/**
 * Created by Damiano on 14/11/2014.
 */
public class EventActivity extends FragmentActivity implements TabListener , DetailsFragment.OnFragmentInteractionListener, CommentsFragment.OnFragmentInteractionListener{
    private ViewPager viewPager;
    private TabsEventAdapter mAdapter;
    private ActionBar actionBar;

    public static final String PARAM_ID_EVENT = "PARAM_ID_EVENT";
    public static final String PARAM_ID_PROFILE = "PARAM_ID_PROFILE";

    public Integer eventID;
    public Integer profileID;

    // Tab titles
    private String[] tabs = { "Details", "Comments"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsEventAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Récupération de l'ID de l'event
        eventID = getIntent().getExtras().getInt(PARAM_ID_EVENT);
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
