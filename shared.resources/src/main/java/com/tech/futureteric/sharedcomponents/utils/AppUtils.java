package com.tech.futureteric.sharedcomponents.utils;

import android.content.Context;
import android.widget.Toast;

public class AppUtils {

    private static long mBackPressed;
    private final static int BACK_BUTTON_TIME_INTERVAL = 2000;

    public static boolean isItOnDoubleBackButton(Context context) {
        if (mBackPressed + BACK_BUTTON_TIME_INTERVAL > System.currentTimeMillis())
            return true;
        else
            Toast.makeText(context, "Tap back button in order to exit", Toast.LENGTH_SHORT).show();

        mBackPressed = System.currentTimeMillis();

        return false;
    }

}
