package com.tech.futureteric.sharedcomponents;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DataType {

    // TODO refactor this class to be more optimal
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FETCHING_GOT_EXCEPTION, NO_DATA_FOUND, DEFAULT, SIGN_IN, USER_FRIENDS_STATUS, USER_EMAIL_BY_UID,
            USER_PHONE_BY_UID, GETTING_BACKGROUND_IMAGE, MORNING_IMAGE_CACHED_SUCCESS,
            MORNING_IMAGE_CACHED_ERROR, MORNING_IMAGE_ONLINE_SUCCESS, MORNING_IMAGE_ONLINE_ERROR,
            DIALOG_ON_POSITIVE, LOCK_SCREEN_MORNING_IMAGE_IS_CACHED, LOCK_SCREEN_MORNING_IMAGE_ERROR})
    public @interface ModelReceivedDataType {}
    public static final int FETCHING_GOT_EXCEPTION = -2;
    public static final int NO_DATA_FOUND = -1;
    public static final int DEFAULT = 0;
    public static final int SIGN_IN = 1;
    public static final int USER_FRIENDS_STATUS = 2;
    public static final int USER_EMAIL_BY_UID = 3;
    public static final int USER_PHONE_BY_UID = 4;
    public static final int GETTING_BACKGROUND_IMAGE = 5;
    public static final int FINDING_FRIENDS = 6;
    
    public static final int PERMISSION_STATUS_WRITE_TO_EXTERNAL_STORAGE = 7;
    
    public static final int MORNING_IMAGE_CACHED_SUCCESS = 8;
    public static final int MORNING_IMAGE_CACHED_ERROR = 9;
    public static final int MORNING_IMAGE_ONLINE_SUCCESS = 10;
    public static final int MORNING_IMAGE_ONLINE_ERROR = 11;
    
    public static final int DIALOG_ON_POSITIVE = 12;

    public static final int LOCK_SCREEN_MORNING_IMAGE_IS_CACHED = 13;
    public static final int LOCK_SCREEN_MORNING_IMAGE_ERROR = 14;
    public static final int LOCK_SCREEN_GOT_DATA_FROM_DATABASE = 15;

    public static final int AUTH_USER_LOGGED_IN = 16;
}
