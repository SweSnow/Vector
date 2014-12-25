package com.simon.vector;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.PopupMenu;

import com.devspark.robototextview.widget.RobotoButton;
import com.devspark.robototextview.widget.RobotoTextView;

public class DetailFragment extends Fragment {

    private ImageView image;
    private CollapsingTitleLayout collapsingTitleLayout;

    public static Shot shot;

    public static DetailFragment newInstance(Shot shot) {
        DetailFragment.shot = shot;
        return new DetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        image = (ImageView) rootView.findViewById(R.id.detail_image);
/*
        collapsingTitleLayout = (CollapsingTitleLayout) rootView.findViewById(R.id.backdrop_toolbar);
        collapsingTitleLayout.setTitle("FireArt Blog");*/

        final LinearLayout header = (LinearLayout) rootView.findViewById(R.id.detail_header);
        /*
        NotifyingScrollView notifyingScrollView = (NotifyingScrollView) rootView.findViewById(R.id.detail_notifying_scrollview);
        notifyingScrollView.setOnScrollChangedListener(new NotifyingScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                collapsingTitleLayout.setScrollOffset((float)((t*2f) + getStatusBarHeight()) / (image.getMeasuredHeight()));
                H.log((float) t / image.getMeasuredHeight());
            }
        });

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                collapsingTitleLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, image.getMeasuredHeight()));
            }
        };

        handler.postDelayed(r, 10);
*/


        /*

        title
        creator
        date
        description


        meta:
            ()bucket
            views
            tags
        actions:
            fullscreen
            like
            share

        comments:
            ?sort
            comment




         */

        RobotoTextView detailTitle = (RobotoTextView) rootView.findViewById(R.id.detail_title);
        RobotoTextView detailDescription = (RobotoTextView) rootView.findViewById(R.id.detail_description);

        RobotoButton detailPerson = (RobotoButton) rootView.findViewById(R.id.detail_person);
        RobotoButton detailColor = (RobotoButton) rootView.findViewById(R.id.detail_color);
        RobotoButton detailTags = (RobotoButton) rootView.findViewById(R.id.detail_tags);
        RobotoTextView detailDate = (RobotoTextView) rootView.findViewById(R.id.detail_date);
        RobotoTextView detailLikes = (RobotoTextView) rootView.findViewById(R.id.detail_likes);
        RobotoTextView detailViews = (RobotoTextView) rootView.findViewById(R.id.detail_views);

        detailColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);

                Menu menu = popupMenu.getMenu();
                menu.add("#009688");
                menu.add("#FF0000");
                menu.add("#123456");

                popupMenu.show();
            }
        });

        if (shot.getTags().size() == 0) {
            detailTags.setClickable(false);
            detailTags.setEnabled(false);
        }

        detailTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);

                Menu menu = popupMenu.getMenu();
                for (int i = 0; i < shot.getTags().size(); i++) {
                    menu.add(shot.getTags().get(i).toString());
                }

                popupMenu.show();
            }
        });

        detailPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EntityDetailActivity.class);
                intent.putExtra("entity", shot.getUser());

                startActivity(intent);
            }
        });

        detailTitle.setText(shot.getTitle());
        detailDescription.setText(shot.getDescription());

        detailPerson.setText(shot.getUser().getName());


        return rootView;
    }
}
