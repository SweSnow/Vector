package com.simon.vector;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewFragment extends Fragment {

    public static Bitmap image;
    public static Shot shot;

    public static PhotoViewFragment newInstance(Bitmap bitmap, Shot shot) {
        PhotoViewFragment.image = bitmap;
        PhotoViewFragment.shot = shot;
        return new PhotoViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_photo_view, null);

        final ImageView imageViewTransition = (ImageView) rootView.findViewById(R.id.photo_view_image);
        imageViewTransition.setImageBitmap(image);
        PhotoViewAttacher attacherTransition = new PhotoViewAttacher(imageViewTransition);

        final String hiDpiUrl = Resource.getImageUrl(shot.getImages(), Images.RESOLUTION_HIDPI);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.photo_view_image);

        Picasso.with(getActivity())
                .load(hiDpiUrl)
                .skipMemoryCache()
                .into(imageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
                        imageViewTransition.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                    }
                });

        return rootView;
    }


}
