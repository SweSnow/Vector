package com.simon.vector;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class H {

    public static void log(String log) {
        Log.d("VECTOR DEBUG", log);
    }

    public static void log(int log) {
        Log.d("VECTOR DEBUG", log + "");
    }

    public static void log(float log) {
        Log.d("VECTOR DEBUG", log + "");
    }

    public static void toast(String text, Context ctx) {
        Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
    }

}
