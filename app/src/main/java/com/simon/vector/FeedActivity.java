package com.simon.vector;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.TintSpinner;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.shamanland.fab.FloatingActionButton;

public class FeedActivity extends ActionBarActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static int secondaryToolbarHeight = 0;
    public static LinearLayout secondaryToolbar = null;

    private TintSpinner toolbarSpinnerSortBy, toolbarSpinnerSortWhen;

    private FeedFragment feedFragment;

    private boolean onCreateDone = false;

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

        toolbarSpinnerSortBy = (TintSpinner) findViewById(R.id.feed_toolbar_spinner_by);
        ArrayAdapter<CharSequence> adapterBy = ArrayAdapter.createFromResource(this,
                R.array.sort_by, R.layout.feed_spinner_layout);
        adapterBy.setDropDownViewResource(R.layout.feed_spinner_dropdown_layout);
        toolbarSpinnerSortBy.setAdapter(adapterBy);

        toolbarSpinnerSortWhen = (TintSpinner) findViewById(R.id.feed_toolbar_spinner_when);
        ArrayAdapter<CharSequence> adapterWhen = ArrayAdapter.createFromResource(this,
                R.array.sort_when, R.layout.feed_spinner_layout);
        adapterWhen.setDropDownViewResource(R.layout.feed_spinner_dropdown_layout);
        toolbarSpinnerSortWhen.setAdapter(adapterWhen);

        toolbarSpinnerSortBy.post(new Runnable() {
            @Override
            public void run() {
                toolbarSpinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (onCreateDone) {
                            refreshSpinnerData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        toolbarSpinnerSortWhen.post(new Runnable() {
            @Override
            public void run() {
                toolbarSpinnerSortWhen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (onCreateDone) {
                            refreshSpinnerData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {

            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());

            if (Resource.isLOrAbove()) {
                ImageButton fab = (ImageButton) findViewById(R.id.feed_fab_l);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fab.getLayoutParams();
                params.setMargins(0, Resource.getStatusBarHeight(getResources()) + actionBarHeight + Resource.getPx(48, getResources()) - Math.round(getResources().getDimension(R.dimen.fab_size) / 2), Math.round(getResources().getDimension(R.dimen.feed_side_margin)), 0);

                fab.setLayoutParams(params);

                fab.setOnClickListener(fabClickListener);
            } else if (Resource.isKkOrAbove()) {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.feed_fab);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fab.getLayoutParams();
                params.setMargins(0, Resource.getStatusBarHeight(getResources()) + actionBarHeight + Resource.getPx(48, getResources()) - (Resource.getPx(56, getResources()) / 2), Math.round(getResources().getDimension(R.dimen.feed_side_margin)), 0);

                fab.setLayoutParams(params);

                fab.setOnClickListener(fabClickListener);
            } else {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.feed_fab);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fab.getLayoutParams();
                params.setMargins(0, actionBarHeight + Resource.getPx(48, getResources()) - (Resource.getPx(56, getResources()) / 2), Math.round(getResources().getDimension(R.dimen.feed_side_margin)), 0);

                fab.setLayoutParams(params);

                fab.setOnClickListener(fabClickListener);
            }
        }

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        onCreateDone = true;
        H.log("STARTED");

    }

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void refreshSpinnerData() {
        H.log("DETTA HÄNDER BARA OM STARTED HAR KALLATS");
        updateFeedFragment(
                new ApiFormatter()
                        .addQuery("shots/")
                        .addOption("page=1")
                        .addOption(appendSortWhen(toolbarSpinnerSortBy.getSelectedItemPosition()))
                        .addOption(appendSortBy(toolbarSpinnerSortBy.getSelectedItemPosition()))
                        .addOption(ApiFormatter.ACCESS_TOKEN));
    }

    private String appendSortBy(int selected) {
        String[] sortByValues = new String[] {null, "comments", "recent", "views"};
        return sortByValues[selected];
    }

    private String appendSortWhen(int selected) {
        String[] sortWhenValue = new String[] {null, "week", "month", "year", "ever"};
        return sortWhenValue[selected];
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

        final FeedActivity self = this;

        MenuItem searchItem = menu.findItem(R.id.feed_menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                secondaryToolbar.startAnimation(AnimationUtils.loadAnimation(self, R.anim.secondary_toolbar_in));
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondaryToolbar.startAnimation(AnimationUtils.loadAnimation(self, R.anim.secondary_toolbar_out));
            }
        });

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
