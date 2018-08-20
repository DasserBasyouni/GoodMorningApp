package com.tech.futureteric.sharedcomponents.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

public class IconUtils {

    public static Drawable getEmailDrawable(Context context){
        return new IconicsDrawable(context)
                .icon(Ionicons.Icon.ion_email)
                .color(Color.WHITE)
                .sizeDp(24);
    }

    public static Drawable getPhoneDrawable(Context context){
        return new IconicsDrawable(context)
                .icon(Ionicons.Icon.ion_ios_telephone)
                .color(Color.WHITE)
                .sizeDp(24);
    }

    public static Drawable getSearchDrawable(Context context){
        return new IconicsDrawable(context)
                .icon(Ionicons.Icon.ion_android_search)
                .color(Color.WHITE)
                .sizeDp(24);
    }

    public static Drawable getAddFriendDrawable(Context context){
        return new IconicsDrawable(context)
                .icon(Ionicons.Icon.ion_android_person_add)
                .color(Color.RED)
                .sizeDp(24);
    }

    public static Drawable getPersonDrawable(Context context){
        return new IconicsDrawable(context)
                .icon(Ionicons.Icon.ion_android_person)
                .color(Color.GRAY)
                .sizeDp(24);
    }
}
