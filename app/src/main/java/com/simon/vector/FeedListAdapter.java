package com.simon.vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.SimpleViewHolder> {

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private ArrayList<Shot> mItems = new ArrayList<Shot>();
    public int page = 1;

    public ListLoadCallback loadCallback = null;
    public ListClickCallback clickCallback = null;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView creator;
        public final ImageView image;
        public final FrameLayout background;
        public final TextView gifTag;
        public final Button overlay;
        public final View layout;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.feed_list_title);
            creator = (TextView) view.findViewById(R.id.feed_list_creator);
            image = (ImageView) view.findViewById(R.id.feed_list_image);
            background = (FrameLayout) view.findViewById(R.id.feed_list_background);
            gifTag = (TextView) view.findViewById(R.id.feed_list_gif_tag);
            overlay = (Button) view.findViewById(R.id.feed_list_overlay);
            layout = view;
        }
    }

    public FeedListAdapter(Context context, RecyclerView recyclerView, int startPage) {
        mContext = context;
        mRecyclerView = recyclerView;
        page = startPage;
    }

    public void setData(ArrayList<Shot> items) {
        mItems = items;
    }

    public void addItem(int position, Shot shot) {
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
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {
        Shot shot = mItems.get(position);

        holder.overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallback.onListItemClick(position, holder.layout);
            }
        });

        holder.title.setText(shot.getTitle());
        holder.creator.setText(shot.getUser().getName());

        String imgUrl = Resource.getImageUrl(shot.getImages(), Images.RESOLUTION_NORMAL);

        if (imgUrl.endsWith(".gif")) {
            holder.gifTag.setVisibility(View.VISIBLE);
        } else {
            holder.gifTag.setVisibility(View.GONE);
        }

        Picasso.with(mContext)
                .load(imgUrl)
                .placeholder(R.drawable.image_placeholder)
                .transform(PaletteTransformation.instance())
                .into(holder.image, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) holder.image.getDrawable()).getBitmap();
                        mItems.get(position).getImages().setImage(bitmap);
                        Palette palette = PaletteTransformation.getPalette(bitmap);
                        holder.background.setBackgroundColor(palette.getDarkMutedColor(palette.getMutedColor(mContext.getResources().getColor(R.color.default_background))));
                    }
                });

        int threshold = 0;
        for (int i = 0; i < page; i++) {
            threshold += 12;
        }

        threshold -= Options.getFeedLoadThreshold();

        if (position > threshold && loadCallback != null) {
            loadCallback.onRequestLoadMore(page, ++page);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface ListLoadCallback {
        public void onRequestLoadMore(int oldPage, int newPage);
    }

    public interface ListClickCallback {
        public void onListItemClick(int position, View view);
    }

}