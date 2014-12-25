package com.simon.vector;

import android.content.res.Resources;
import android.os.Build;

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

}
