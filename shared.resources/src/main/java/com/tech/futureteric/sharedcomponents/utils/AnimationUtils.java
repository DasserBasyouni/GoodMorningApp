package com.tech.futureteric.sharedcomponents.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;

public class AnimationUtils {

    public static void startActivityWithAnimation(Intent intent, Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent,  ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
