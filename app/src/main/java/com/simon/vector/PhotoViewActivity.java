package com.simon.vector;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class PhotoViewActivity extends ActionBarActivity {

    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, (Fragment) PhotoViewFragment.newInstance((Bitmap) getIntent().getExtras().get("image")))
                    .commit();
        }

        imageUrl = (String) getIntent().getExtras().get("url");

        Toolbar toolbar = (Toolbar) findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((String) getIntent().getExtras().get("title"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.photo_share) {

            //TODO share
            return true;
        } else if (id == R.id.photo_save) {
            //TODO save
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
