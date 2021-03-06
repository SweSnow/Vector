package com.simon.vector;

import android.graphics.Bitmap;
import android.os.Environment;
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

import com.nispok.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;

public class PhotoViewActivity extends ActionBarActivity {

    private String imageUrl;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        if (savedInstanceState == null) {

            image = (Bitmap) getIntent().getExtras().get("image");

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.photo_container, PhotoViewFragment.newInstance(image, (Shot) getIntent().getExtras().get("shot")))
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

            File directory = new File("/Pictures/Vector");
            if (directory.mkdir()) {
                String filename = "/Pictures/Vector/" + getIntent().getExtras().get("title") + ".jpg";
                File sd = Environment.getExternalStorageDirectory();
                File dest = new File(sd, filename);

                try {
                    FileOutputStream out = new FileOutputStream(dest);
                    image.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();

                    Snackbar.with(this)
                            .text(getString(R.string.save_success) + " " +  sd.getPath() + filename)
                            .show(this);

                } catch (Exception e) {
                    e.printStackTrace();

                    Snackbar.with(this)
                            .text(R.string.save_fail).
                            show(this);
                }
            } else {
                Snackbar.with(this)
                        .text(R.string.save_fail).
                        show(this);
            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
