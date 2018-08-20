package com.tech.futureteric.backend.utils.user;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Source;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

import static com.tech.futureteric.backend.Constants.EMAIL_By_UID;
import static com.tech.futureteric.backend.Constants.PHONE_By_UID;
import static com.tech.futureteric.backend.utils.AppFirestoreUtils.getDatabaseWithDocument;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.getUserUid;

public class UsersSearchUtils {

    public static void searchForUserByPhone(Activity activity, String phoneNumber) {
        searchForUser(activity, PHONE_By_UID, phoneNumber);
    }

    public static void searchForUserByEmail(Activity activity, String email) {
        searchForUser(activity, EMAIL_By_UID, email);
    }

    private static void searchForUser(Activity activity, String path, String data) {
        getDatabaseWithDocument("Search_" + path + "/" + data).get(Source.SERVER)
                .addOnCompleteListener(activity, task -> {

                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {
                            Map<String, Object> sendData = document.getData();
                            if (sendData != null) {
                                Log.e("Z_", "here");
                                Log.e("Z_", "DocumentSnapshot data: " + sendData.toString());
                                IdlingDataModel.getInstance().setData(sendData, DataType.DEFAULT);
                                return;
                            }
                        }

                        IdlingDataModel.getInstance().setData(null, DataType.NO_DATA_FOUND);
                    } else {
                        IdlingDataModel.getInstance().setData(null,  DataType.DEFAULT);
                        Timber.v(task.getException(), "searchForUser with ");
                    }

                });
    }

    private static void addUserDataToSearchDatabase(Activity activity, String path, String data) {
        getDatabaseWithDocument("Search_"+ path + "/").set(
                new HashMap<String, String>(){{put(data, getUserUid());}}
        ).addOnCompleteListener(activity, task -> {

            if (task.isSuccessful()){
                Timber.i("Task is successful");
            } else
                Timber.v(task.getException());
        });
    }
}