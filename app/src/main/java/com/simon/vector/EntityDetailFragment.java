package com.simon.vector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nispok.snackbar.Snackbar;

public class EntityDetailFragment extends Fragment {

    public static User user;
    public static Team team;

    public static int mode = EntityDetailFragment.USER;
    public final static int USER = 1;
    public final static int TEAM = 1;

    public static EntityDetailFragment newInstance(User user) {
        EntityDetailFragment.user = user;
        EntityDetailFragment.mode = USER;
        return new EntityDetailFragment();
    }

    public static EntityDetailFragment newInstance(Team team) {
        EntityDetailFragment.team = team;
        EntityDetailFragment.mode = TEAM;
        return new EntityDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entity_detail, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.entity_toolbar);
        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);

        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mode == USER) {
            ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(user.getName());
        } else if (mode == TEAM) {
            ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(team.getName());
        }

        return rootView;
    }

    private View.OnClickListener mapClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + user.getLocation()));
            if (geoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(geoIntent); // Initiate lookup
            } else {
                Snackbar.with(getActivity())
                        .text(R.string.no_app_location)
                        .show(getActivity());
            }
        }
    };
}