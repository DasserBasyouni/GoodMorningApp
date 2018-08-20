package com.tech.futureteric.backend.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class Utils {

    public static void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    public static void enablePicassoCaching(Context context) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context,Integer.MAX_VALUE));
        Picasso built = builder.build();
        //built.setIndicatorsEnabled(true);
        //built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }
}
