package com.simon.vector;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class EntityDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_detail);
        if (savedInstanceState == null) {

            if (getIntent().getExtras().get("entity") instanceof User) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, EntityDetailFragment.newInstance((User) getIntent().getExtras().get("entity")))
                        .commit();
            } else if (getIntent().getExtras().get("entity") instanceof Team) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, EntityDetailFragment.newInstance((Team) getIntent().getExtras().get("entity")))
                        .commit();
            }

        }
    }
}
