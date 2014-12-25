package com.simon.vector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.SimpleViewHolder> {
    private static final int COUNT = 100;

    private final Context mContext;
    private final TwoWayView mRecyclerView;
    private ArrayList<Shot> mItems = new ArrayList<Shot>();
    private int mCurrentItemId = 0;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView creator;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.feed_list_title);
            creator = (TextView) view.findViewById(R.id.feed_list_creator);
        }
    }

    public FeedListAdapter(Context context, TwoWayView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
    }

    public void setData(ArrayList<Shot> items) {
        mItems = items;
    }

    public void addItem(int position, Shot shot) {
        final int id = mCurrentItemId++;
        mItems.add(position, shot);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public Shot getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.feed_list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        Shot shot = mItems.get(position);

        holder.title.setText(shot.getTitle());
        holder.creator.setText(shot.getUser().getName());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}