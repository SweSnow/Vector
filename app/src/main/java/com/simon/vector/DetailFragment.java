package com.simon.vector;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.PopupMenu;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoButton;
import com.devspark.robototextview.widget.RobotoTextView;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.makeramen.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {

    public static ImageView image;
    public static ImageView imageTransition;
    private ImageView imageGif;

    public static Shot shot;

    public static DetailFragment newInstance(Shot shot) {
        DetailFragment.shot = shot;
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        final LinearLayout header = (LinearLayout) rootView.findViewById(R.id.detail_header);
        final LinearLayout subheader = (LinearLayout) rootView.findViewById(R.id.detail_subheader);

        final RoundedImageView profileImageView = (RoundedImageView) rootView.findViewById(R.id.detail_profile);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EntityDetailActivity.class);
                intent.putExtra("entity", shot.getUser());

                startActivity(intent);
            }
        });

        image = (ImageView) rootView.findViewById(R.id.detail_image);
        imageTransition = (ImageView) rootView.findViewById(R.id.detail_image_transition);
        imageGif = (ImageView) rootView.findViewById(R.id.detail_image_gif);

        Picasso.with(getActivity())
                .load(shot.getUser().getAvatar_url())
                .placeholder(R.drawable.ic_person)
                .into(profileImageView);

        Bitmap bitmap = shot.getImages().getImage();

        if (bitmap != null) {
            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    header.setBackgroundColor(
                            palette.getDarkMutedColor(
                                    palette.getMutedColor(
                                            getResources().getColor(R.color.default_background))));
                   /* subheader.setBackgroundColor(
                            palette.getDarkVibrantColor(
                                    palette.getVibrantColor(
                                            getResources().getColor(R.color.default_background))));*/
                }
            });

            imageTransition.setImageBitmap(bitmap);

        }

        final String hiDpiUrl = Resource.getImageUrl(shot.getImages(), Images.RESOLUTION_HIDPI);

        if (shot.getImages().getNormal().endsWith(".gif")) {

            imageTransition.setVisibility(View.VISIBLE);

            ViewTreeObserver vto = imageTransition.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    imageTransition.getViewTreeObserver().removeOnPreDrawListener(this);
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageTransition.getLayoutParams();
                    params.height = imageTransition.getMeasuredHeight();
                    params.width = imageTransition.getMeasuredWidth();

                    imageGif.setLayoutParams(params);
                    imageGif.setVisibility(View.VISIBLE);
                    return true;
                }
            });

            Ion.with(imageGif)
                    .load(hiDpiUrl)
                    .setCallback(new FutureCallback<ImageView>() {
                @Override
                public void onCompleted(Exception e, ImageView result) {
                    image.setVisibility(View.GONE);
                    imageTransition.setVisibility(View.GONE);
                }
            });
        } else if (!hiDpiUrl.equals(shot.getImages().getNormal())) {
            Picasso.with(getActivity())
                    .load(hiDpiUrl)
                    .skipMemoryCache()
                    .into(image, new Callback.EmptyCallback() {
                        @Override
                        public void onSuccess() {
                            imageTransition.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                        }
                    });

        }

        rootView.findViewById(R.id.detail_image_button).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhotoViewActivity.class);

                imageTransition.setVisibility(View.VISIBLE);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        getActivity(),
                        imageTransition,
                        "feedTransition");

                intent.putExtra("image", shot.getImages().getImage());
                intent.putExtra("title", shot.getTitle());
                intent.putExtra("shot", shot);

                String imgUrl = Resource.getImageUrl(shot.getImages(), Images.RESOLUTION_HIDPI);

                intent.putExtra("url", imgUrl);

                startActivity(intent, options.toBundle());
            }
        });

        if (TextUtils.isEmpty(shot.getDescription())) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) profileImageView.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
        }

        RobotoTextView detailTitle = (RobotoTextView) rootView.findViewById(R.id.detail_title);
        RobotoTextView detailDescription = (RobotoTextView) rootView.findViewById(R.id.detail_description);

        RobotoButton detailPerson = (RobotoButton) rootView.findViewById(R.id.detail_person);
        RobotoButton detailBucket = (RobotoButton) rootView.findViewById(R.id.detail_bucket);
        RobotoButton detailTags = (RobotoButton) rootView.findViewById(R.id.detail_tags);
        RobotoTextView detailDate = (RobotoTextView) rootView.findViewById(R.id.detail_date);
        RobotoTextView detailLikes = (RobotoTextView) rootView.findViewById(R.id.detail_likes);
        RobotoTextView detailViews = (RobotoTextView) rootView.findViewById(R.id.detail_views);

        detailBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CONT
            }
        });

        int month = Integer.parseInt(shot.getCreated_at().substring(5, 7));

        String mn = "";

        switch (month) {
            case 1:
                mn = getString(R.string.january_short);
                break;
            case 2:
                mn = getString(R.string.february_short);
                break;
            case 3:
                mn = getString(R.string.march_short);
                break;
            case 4:
                mn = getString(R.string.april_short);
                break;
            case 5:
                mn = getString(R.string.may_short);
                break;
            case 6:
                mn = getString(R.string.june_short);
                break;
            case 7:
                mn = getString(R.string.july_short);
                break;
            case 8:
                mn = getString(R.string.august_short);
                break;
            case 9:
                mn = getString(R.string.september_short);
                break;
            case 10:
                mn = getString(R.string.october_short);
                break;
            case 11:
                mn = getString(R.string.november_short);
                break;
            case 12:
                mn = getString(R.string.december_short);
                break;
            default:
                break;
        }

		detailDate.setText(mn + " " + ((shot.getCreated_at().substring(8, 10).startsWith("0")) ? shot.getCreated_at().substring(9, 10) : shot.getCreated_at().substring(8, 10)) + ", " + shot.getCreated_at().substring(0, 4));

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
        detailDescription.setText(getDescription(shot.getDescription()));
        detailDescription.setMovementMethod(LinkMovementMethod.getInstance());
        detailPerson.setText(shot.getUser().getName());

        int likes = shot.getLikes_count().intValue();
        int views = shot.getViews_count().intValue();

        if (likes == 1) {
            detailLikes.setText(likes + " " + getString(R.string.likes_single));
        } else {
            detailLikes.setText(likes + " " + getString(R.string.likes_plural));
        }

        if (views == 1) {
            detailViews.setText(views + " " + getString(R.string.views_single));
        } else {
            detailViews.setText(views + " " + getString(R.string.views_plural));
        }

        return rootView;
    }

    private CharSequence getDescription(String text) {
        if (text == null) {
            return null;
        }

        Spanned description = Html.fromHtml(text);

        int lastChar = 0;

        for (int i = 0; i < description.length(); i++) {
            if (description.charAt(i) != ' ') {
                lastChar = i;
            }
        }

        return description.subSequence(0, lastChar);
    }

}
