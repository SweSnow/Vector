package com.simon.vector;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

public class DetailActivity extends ActionBarActivity {

    private Shot shot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        if (savedInstanceState == null) {

            toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.toolbar_in));

            shot = (Shot) getIntent().getExtras().get("shot");

            Fragment fragment = DetailFragment.newInstance(shot);

            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.detail_settings) {
            return true;
        } else if (id == R.id.detail_fullscreen) {
            Intent intent = new Intent(this, PhotoViewActivity.class);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    DetailFragment.image,
                    "feedTransition");

            Bundle bundle = options.toBundle();

            bundle.putParcelable("image", shot.getImages().getImage());
            bundle.putString("title", shot.getTitle());
            bundle.putParcelable("shot", shot);

            startActivity(intent, bundle);
        } else if (id == R.id.detail_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shot.getHtml_url());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.share)));
        }
        return super.onOptionsItemSelected(item);
    }
}
