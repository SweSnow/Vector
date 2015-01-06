package com.simon.vector;

import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.internal.widget.TintSpinner;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class FeedActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static int secondaryToolbarHeight = 0;
    public static LinearLayout secondaryToolbar = null;

    private FeedFragment feedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        if (savedInstanceState == null) {
            updateFeedFragment(new ApiFormatter().addQuery("shots/").addOption("page=1").addOption(ApiFormatter.ACCESS_TOKEN));
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.feed_toolbar);
        setSupportActionBar(toolbar);

        View statusPusher = findViewById(R.id.feed_status_pusher);
        if (Resource.isKkOrAbove()) {
            statusPusher.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Resource.getStatusBarHeight(getResources())));
        }

        secondaryToolbar = (LinearLayout) findViewById(R.id.feed_secondary_toolbar);
        secondaryToolbarHeight = secondaryToolbar.getLayoutParams().height;

        TintSpinner toolbarSpinnerSortBy = (TintSpinner) findViewById(R.id.feed_toolbar_spinner_by);
        ArrayAdapter<CharSequence> adapterBy = ArrayAdapter.createFromResource(this,
                R.array.sort_by, R.layout.feed_spinner_layout);
        adapterBy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbarSpinnerSortBy.setAdapter(adapterBy);

        toolbarSpinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TintSpinner toolbarSpinnerSortWhen = (TintSpinner) findViewById(R.id.feed_toolbar_spinner_when);
        ArrayAdapter<CharSequence> adapterWhen = ArrayAdapter.createFromResource(this,
                R.array.sort_when, R.layout.feed_spinner_layout);
        adapterWhen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbarSpinnerSortWhen.setAdapter(adapterWhen);

        toolbarSpinnerSortWhen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragmentss
    //    FragmentManager fragmentManager = getSupportFragmentManager();
    //    fragmentManager.beginTransaction()
    //            .replace(R.id.container, new FeedFragment())
    //            .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feed, menu);
/*
        final FeedActivity self = this;

        SearchView searchView = (SearchView) menu.findItem(R.id.feed_menu_search);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondaryToolbar.startAnimation(AnimationUtils.loadAnimation(self, R.anim.toolbar_out));
            }
        });*/

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.feed_menu_refresh) {
            feedFragment.refresh();
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateFeedFragment(ApiFormatter _apiFormatter) {
         feedFragment = FeedFragment.createNewInstance(_apiFormatter);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, feedFragment)
                .commit();
    }

}
