package youp.ingesup.com.youp.view;

import android.app.Activity;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import retrofit.RestAdapter;
import youp.ingesup.com.youp.R;
import youp.ingesup.com.youp.model.services.UserService;
import youp.ingesup.com.youp.view.fragment.CommentsFragment;
import youp.ingesup.com.youp.view.fragment.EventFragment;
import youp.ingesup.com.youp.view.fragment.SignUpFragment;

public class HomeActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, SignUpFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private FrameLayout container;
    private android.support.v4.app.Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HomeActivity", "onCreate() setContent");
        setContentView(R.layout.activity_home);

        Log.e("HomeActivity", "onCreate()");

        mNavigationDrawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        container = (FrameLayout)findViewById(R.id.container);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp( R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        onNavigationDrawerItemSelected(0);


    }

    private static long back_pressed;

    @Override
    public void onBackPressed()
    {

        // TODO fragment go back in stack
        super.onBackPressed();


        //if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        //else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }


    public void tryToUpdateTimeline(){
        if(currentFragment != null){
            EventFragment eventFragment = (EventFragment)currentFragment;
            if(eventFragment != null){
                eventFragment.loadEvents(null);
            }
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        if(container != null) {
            container.removeAllViews();
            Log.e("onNavigationDrawerItemSelected", "container is clean");

            currentFragment = PlaceholderFragment.newInstance(position + 1);

            String tag = String.valueOf(position);

            goToFragment(currentFragment, tag);

            /*
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, currentFragment, tag)
                    .addToBackStack(tag)
                    .commit();

            /**/



        }
        else Log.e("onNavigationDrawerItemSelected", "container is null !!");

        Log.e("HomeActivity", "onNavigationDrawerItemSelected() -> " + position);
    }

    public void goToFragment(final Fragment fragment, String tag){

        currentFragment = fragment;

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit();

        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        Log.e("HomeActivity", "onBackStackChanged() currentFragment : " + fragment.getTag());
                    }
                });
    }

    public void onSectionAttached(int number) {
        Log.e("HomeActivity", "onSectionAttached() -> " + number);
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {

            Log.e("PlaceholderFragment", "newInstance() -> " + sectionNumber);

            Fragment fragment = null;

            if(sectionNumber == 1) {
                fragment = new EventFragment();
            }

            if(sectionNumber == 2){
                fragment = new PlaceholderFragment();
            }


            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            int itemSelected = getArguments().getInt(ARG_SECTION_NUMBER);

            View rootView = inflater.inflate(R.layout.fragment_home, container, false);


            Log.e("PlaceholderFragment", "onCreateView() -> " + itemSelected);

            return rootView;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached( getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
