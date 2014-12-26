package com.simon.vector;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.widget.TwoWayView;

public class FeedFragment extends Fragment {

    public FeedListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String url = "";
    private boolean hasUrl = false;

    private final static String RECYCLER_SCROLL_KEY = "RECYCLER_SCROLL_KEY";
    public final static String SENT_URL_KEY = "SENT_URL_KEY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            url = getArguments().getString(SENT_URL_KEY);
            if (!TextUtils.isEmpty(url)) {
                hasUrl = true;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        TwoWayView recyclerView = (TwoWayView) rootView.findViewById(R.id.list);
        recyclerView.setHasFixedSize(false);

        adapter = new FeedListAdapter(getActivity(), recyclerView);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData();

        ItemClickSupport itemClick = ItemClickSupport.addTo(recyclerView);

        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int pos, long l) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        getActivity(),
                        view.findViewById(R.id.feed_list_image),
                        "feedTransition");
                intent.putExtra("shot", adapter.getItem(pos));

                getActivity().startActivity(intent, options.toBundle());
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.feed_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_primary, R.color.color_accent);

        swipeRefreshLayout.setEnabled(false);

        return rootView;
    }

    private void loadData() {

        VolleySingleton volleySingleton = VolleySingleton.getInstance(getActivity());
        volleySingleton.addToRequestQueue(new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response

                        Gson gson = new Gson();
                        Shot shot = gson.fromJson(response, Shot.class);

                        for (int i = 0; i < 10; i++) {
                            adapter.addItem(i, shot);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
