package youp.ingesup.com.youp.view.fragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import youp.ingesup.com.youp.view.fragment.LoginFragment;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.adapter.TabsLoginSignUpAdapter;

import youp.ingesup.com.youp.view.fragment.SignUpFragment;


public class MainLoginFragment extends Fragment implements
        ActionBar.TabListener {

    public final static String PARAM_GO_SIGN_UP = "PARAM_GO_SIGN_UP";

    private ViewPager viewPager;
    private TabsLoginSignUpAdapter mAdapter;
    private ActionBar actionBar;

    // Tab titles
    private String[] tabs = { "Login", "Sign Up"};

    public static MainLoginFragment newInstance(boolean goSignUp){
        MainLoginFragment fragment = new MainLoginFragment();

        Bundle b = new Bundle();
        b.putBoolean(PARAM_GO_SIGN_UP, goSignUp);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.activity_login, container, false);

        // Initilization
        viewPager = (ViewPager) fragmentView.findViewById(R.id.pager);
        actionBar = getActivity().getActionBar();
        mAdapter = new TabsLoginSignUpAdapter(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        viewPager.setCurrentItem(0);

        // Adding Tab
        actionBar.removeAllTabs();
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        viewPager.setCurrentItem((getArguments().getBoolean(PARAM_GO_SIGN_UP)) ? 1 : 0);

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                //actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });



        boolean goSignUp = getActivity().getIntent().getBooleanExtra(PARAM_GO_SIGN_UP, false);
        if(goSignUp){
            viewPager.setCurrentItem(1);
        }



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
}