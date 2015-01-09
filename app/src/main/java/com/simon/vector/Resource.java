package com.simon.vector;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

public class Resource {

    public static int getStatusBarHeight(Resources res) {
        int result = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static boolean isLOrAbove() {
        return Build.VERSION.SDK_INT >= 21;
    }
    public static boolean isKkOrAbove() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static int getPx(int dp, Context ctx) {
        return (int) (dp * ctx.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static String getBestImageUrl(Images images) {
        if (!TextUtils.isEmpty(images.getHidpi())) {
            return images.getHidpi();
        } else if (!TextUtils.isEmpty(images.getNormal())) {
            return images.getNormal();
        } else {
            return images.getTeaser();
        }
    }

}
