package com.simon.vector;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class BucketListActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new EntityDetailFragment())
                    .commit();

        }
    }
}
