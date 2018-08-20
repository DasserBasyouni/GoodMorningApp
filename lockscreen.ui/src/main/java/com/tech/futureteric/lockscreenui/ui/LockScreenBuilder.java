package com.tech.futureteric.lockscreenui.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.tech.futureteric.backend.model.Message;

import java.util.ArrayList;
import java.util.List;

import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_FRIEND_TOKEN;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_FRIEND_UID;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_IS_PREVIEW;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_MESSAGES_LIST;


public class LockScreenBuilder {

    public static void buildThenShowLockScreen(Context context, List<Message> messages){
        Intent intent = new Intent(context, LockScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle bundle = new Bundle();
        intent.putParcelableArrayListExtra(BUNDLE_MESSAGES_LIST, (ArrayList<? extends Parcelable>) messages);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void buildPreview(Context context, String friendUid, String friendToken){
        Intent intent = new Intent(context, LockScreenActivity.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_IS_PREVIEW, true);
        bundle.putString(BUNDLE_FRIEND_UID, friendUid);
        bundle.putString(BUNDLE_FRIEND_TOKEN, friendToken);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
