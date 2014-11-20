package youp.ingesup.com.youp.view.fragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.adapter.TabsEventAdapter;

import static android.app.ActionBar.TabListener;

/**
 * Created by Damiano on 14/11/2014.
 */
public class MainEventFragment extends Fragment implements TabListener , DetailsFragment.OnFragmentInteractionListener {
    private ViewPager viewPager;
    private TabsEventAdapter mAdapter;
    private ActionBar actionBar;

    public static final String PARAM_ID_EVENT = "PARAM_ID_EVENT";
    public static final String PARAM_ID_PROFILE = "PARAM_ID_PROFILE";

    public static Integer eventID;
    public int profileID;

    // Tab titles
    private String[] tabs = { "Details", "Comments"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.activity_event, container, false);

        // Initilization
        viewPager = (ViewPager) fragmentView.findViewById(R.id.pager);
        actionBar = getActivity().getActionBar();
        mAdapter = new TabsEventAdapter(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Récupération de l'ID de l'event
        eventID = getActivity().getIntent().getExtras().getInt(PARAM_ID_EVENT);
        profileID = getActivity().getIntent().getExtras().getInt(PARAM_ID_PROFILE);

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

        return fragmentView;
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
