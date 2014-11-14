package youp.ingesup.com.youp.view;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.SignUpFragment;
import youp.ingesup.com.youp.adapter.MyAccountAdapter;
import youp.ingesup.com.youp.view.fragment.LoginFragment;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class MyAccountActivity extends FragmentActivity implements
        ActionBar.TabListener, LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener {


    private String[] tabs = { "Friends", "Events", "My Profile"};

    private ViewPager viewPager;
    private MyAccountAdapter adapter;
    private ActionBar actionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new MyAccountAdapter(getSupportFragmentManager());
        actionBar = getActionBar();

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }
            @Override
            public void onPageScrollStateChanged(int arg0) { }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
