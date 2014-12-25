package com.simon.vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EntityDetailFragment extends Fragment {

    public static User user;

    public static EntityDetailFragment newInstance(User user) {
        EntityDetailFragment.user = user;
        return new EntityDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entity_detail, container, false);
        return rootView;
    }
}