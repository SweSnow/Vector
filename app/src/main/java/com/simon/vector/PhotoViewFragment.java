package com.simon.vector;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewFragment extends Fragment {

    public static Bitmap image;

    public static PhotoViewFragment newInstance(Bitmap bitmap) {
        PhotoViewFragment.image = bitmap;
        return new PhotoViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_view, null);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.photo_view_image);
        imageView.setImageBitmap(image);
        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);

        return rootView;
    }


}
