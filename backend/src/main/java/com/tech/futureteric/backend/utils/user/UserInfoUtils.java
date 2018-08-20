package com.tech.futureteric.backend.utils.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import timber.log.Timber;

import static com.tech.futureteric.backend.utils.AppFirestoreUtils.getDatabaseWithDocument;

public class UserInfoUtils {

    public static boolean isUserLoggedIn(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static boolean isUserIsNew(){
        // in case metadata or user == null then isUserIsNew() will return true
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseUserMetadata metadata = user.getMetadata();
            return metadata == null || metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp();
        }
        return true;
    }

    public static void addUserFcmTokenToDatabase(String token) {
        String data = getUser().getPhoneNumber();

        if (data != null)
            saveTokenToPath("Search_PhoneByUid/" + data, token);

        data = getUser().getEmail();
        if (data != null)
            saveTokenToPath("Search_EmailByUid/" + data, token);
    }

    // TODO fix if user have multi-device token would be just one of the last saved device
    private static void saveTokenToPath(String path, String token) {
        getDatabaseWithDocument(path).set(
                new HashMap<String, Object>() {{
                    put("token", token);
                }}, SetOptions.merge()
        ).addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                Timber.i("addUserFcmToken Task is successful");
            } else
                Timber.v(task1.getException());
        });
    }

    public static boolean isThisCurrentUser(String uid){
        return uid.equals(getUserUid());
    }

    private static FirebaseUser getUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static String getUserUid(){
        return getUser().getUid();
    }

    public static String getUserDisplayName(){
        return getUser().getDisplayName();
    }
}