package com.tech.futureteric.backend.utils;

import android.app.Activity;

import java.util.Date;
import java.util.HashMap;

import timber.log.Timber;

import static com.tech.futureteric.backend.utils.AppFirestoreUtils.getDatabaseWithCollection;

public class MessagingUtils {

    public static void sendMessageToUser(Activity activity, String msg, Date atDate, String senderUid,
                                         String senderName, String receiverUid, String token){

        getDatabaseWithCollection("users/MSGs/" + senderUid + "_" + receiverUid)
                .add(new HashMap<String, Object>(){{
                    put("msg", msg);
                    put("time",  atDate);
                    put("uid",  senderUid);
                    put("token",  token);
                    put("senderName", senderName);
                }}).addOnCompleteListener(task -> {

                    if (!task.isSuccessful()){
                        // TODO display error to user
                        Timber.v(task.getException());
                        return;
                    }

                    Timber.i("sendMessageToUser() is successful");
                    activity.finish();
                });
    }


}
