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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

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
                        ArrayList list = gson.fromJson(response, ArrayList.class);

                        for (int i = 0; i < list.size(); i++) {
                            Shot shot = new Shot();

                            shot.setAttachments_count((Number)((LinkedTreeMap) list.get(i)).get("attachments_count"));
                            shot.setAttachments_url((String)((LinkedTreeMap) list.get(i)).get("attachments_url"));
                            shot.setBuckets_count((Number)((LinkedTreeMap) list.get(i)).get("buckets_count"));
                            shot.setBuckets_url((String)((LinkedTreeMap) list.get(i)).get("buckets_url"));
                            shot.setComments_count((Number)((LinkedTreeMap) list.get(i)).get("comments_count"));
                            shot.setComments_url((String)((LinkedTreeMap) list.get(i)).get("comments_url"));
                            shot.setCreated_at((String)((LinkedTreeMap) list.get(i)).get("created_at"));
                            shot.setDescription((String)((LinkedTreeMap) list.get(i)).get("description"));
                            shot.setHeight((Number)((LinkedTreeMap) list.get(i)).get("height"));
                            shot.setHtml_url((String)((LinkedTreeMap) list.get(i)).get("html_url"));
                            shot.setId((Number)((LinkedTreeMap) list.get(i)).get("id"));

                            LinkedTreeMap imagesMap = (LinkedTreeMap)((LinkedTreeMap) list.get(i)).get("images");

                            Images images = new Images();
                            images.setHidpi((String) imagesMap.get("hidpi"));
                            images.setNormal((String) imagesMap.get("normal"));
                            images.setTeaser((String) imagesMap.get("teaser"));

                            shot.setImages(images);
                            shot.setLikes_count((Number)((LinkedTreeMap) list.get(i)).get("likes_count"));
                            shot.setLikes_url((String)((LinkedTreeMap) list.get(i)).get("likes_url"));
                            shot.setProjects_url((String)((LinkedTreeMap) list.get(i)).get("projects_url"));
                            shot.setRebounds_count((Number)((LinkedTreeMap) list.get(i)).get("rebounds_count"));
                            shot.setRebounds_url((String)((LinkedTreeMap) list.get(i)).get("rebounds_url"));
                            shot.setTags((List)((LinkedTreeMap) list.get(i)).get("tags"));

                            LinkedTreeMap teamMap = (LinkedTreeMap)((LinkedTreeMap) list.get(i)).get("team");

                            if (teamMap != null) {
                                Team team = new Team();

                                team.setAvatar_url((String) teamMap.get("avatar_url"));
                                team.setBio((String) teamMap.get("bio"));
                                team.setBuckets_count((Number) teamMap.get("buckets_count"));
                                team.setBuckets_url((String) teamMap.get("buckets_url"));
                                team.setCreated_at((String) teamMap.get("created_at"));
                                team.setFollowers_count((Number) teamMap.get("followers_count"));
                                team.setFollowers_url((String) teamMap.get("followers_url"));
                                team.setFollowing_url((String) teamMap.get("following_url"));
                                team.setFollowings_count((Number) teamMap.get("followings_count"));
                                team.setHtml_url((String) teamMap.get("html_url"));
                                team.setId((Number) teamMap.get("id"));
                                team.setLikes_count((Number) teamMap.get("likes_count"));
                                team.setLikes_url((String) teamMap.get("likes_url"));

                                LinkedTreeMap teamLinksMap = (LinkedTreeMap) teamMap.get("links");

                                Links teamLinks = new Links();

                                teamLinks.setTwitter((String) teamLinksMap.get("twitter"));
                                teamLinks.setWeb((String) teamLinksMap.get("web"));

                                team.setLinks(teamLinks);

                                team.setLocation((String) teamMap.get("location"));
                                team.setMembers_count((Number) teamMap.get("members_count"));
                                team.setMembers_url((String) teamMap.get("members_url"));
                                team.setName((String) teamMap.get("name"));
                                team.setPro((boolean) teamMap.get("pro"));
                                team.setProjects_count((Number) teamMap.get("projects_count"));
                                team.setProjects_url((String) teamMap.get("projects_url"));
                                team.setShots_count((Number) teamMap.get("shots_count"));
                                team.setShots_url((String) teamMap.get("shots_url"));
                                team.setTeam_shots_url((String) teamMap.get("team_shots_url"));
                                team.setType((String) teamMap.get("type"));
                                team.setUpdated_at((String) teamMap.get("updated_at"));
                                team.setUsername((String) teamMap.get("username"));

                                shot.setTeam(team);
                            }

                            shot.setTitle((String)((LinkedTreeMap) list.get(i)).get("title"));
                            shot.setUpdated_at((String)((LinkedTreeMap) list.get(i)).get("updated_at"));

                            LinkedTreeMap userMap = (LinkedTreeMap)((LinkedTreeMap) list.get(i)).get("user");

                            User user = new User();

                            user.setAvatar_url((String) userMap.get("avatar_url"));
                            user.setBio((String) userMap.get("bio"));
                            user.setBuckets_count((Number) userMap.get("buckets_count"));
                            user.setBuckets_url((String) userMap.get("buckets_url"));
                            user.setCreated_at((String) userMap.get("created_at"));
                            user.setFollowers_count((Number) userMap.get("followers_count"));
                            user.setFollowers_url((String) userMap.get("followers_url"));
                            user.setFollowing_url((String) userMap.get("following_url"));
                            user.setFollowings_count((Number) userMap.get("followings_count"));
                            user.setHtml_url((String) userMap.get("html_url"));
                            user.setId((Number) userMap.get("id"));
                            user.setLikes_count((Number) userMap.get("likes_count"));
                            user.setLikes_url((String) userMap.get("likes_url"));

                            LinkedTreeMap linksMap = (LinkedTreeMap) userMap.get("links");

                            Links links = new Links();

                            links.setTwitter((String) linksMap.get("twitter"));
                            links.setWeb((String) linksMap.get("web"));

                            user.setLinks(links);

                            user.setLocation((String) userMap.get("location"));
                            user.setName((String) userMap.get("name"));
                            user.setPro((boolean) userMap.get("pro"));
                            user.setProjects_count((Number) userMap.get("projects_count"));
                            user.setProjects_url((String) userMap.get("projects_url"));
                            user.setShots_count((Number) userMap.get("shots_count"));
                            user.setShots_url((String) userMap.get("shots_url"));
                            user.setTeams_count((Number) userMap.get("teams_count"));
                            user.setTeams_url((String) userMap.get("teams_url"));
                            user.setType((String) userMap.get("type"));
                            user.setUpdated_at((String) userMap.get("updated_at"));
                            user.setUsername((String) userMap.get("username"));

                            shot.setUser(user);
                            shot.setViews_count((Number)((LinkedTreeMap) list.get(i)).get("views_count"));
                            shot.setWidth((Number)((LinkedTreeMap) list.get(i)).get("width"));

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
