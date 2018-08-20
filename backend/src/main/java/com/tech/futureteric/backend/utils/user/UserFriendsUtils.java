package com.tech.futureteric.backend.utils.user;

import android.app.Activity;

import com.tech.futureteric.backend.model.Friend;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.List;

import timber.log.Timber;

import static com.tech.futureteric.backend.utils.AppFirestoreUtils.getDatabaseWithCollection;
import static com.tech.futureteric.backend.utils.AppFirestoreUtils.getDatabaseWithDocument;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.getUserUid;

public class UserFriendsUtils {

    public static void listenToUserFriends(Activity activity) {
        getDatabaseWithCollection("users/contacts/" +  getUserUid()).addSnapshotListener(activity,
                (documentSnapshot, e) -> {

                    if (e != null) {
                        // TODO display msg to user on error
                        Timber.w(e);
                        return;
                    }

                    List<Friend> friends = null;
                    if (documentSnapshot != null && documentSnapshot.size() > 0) {
                        friends = documentSnapshot.toObjects(Friend.class);
                    }

                    IdlingDataModel.getInstance().setData(friends, DataType.FINDING_FRIENDS);
                });
    }

    public static void addFriendToUserContacts(Friend friend) {
        getDatabaseWithDocument("users/contacts/" +  getUserUid() + "/" + friend.getUid()).set(friend)
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()){
                        Timber.v(task.getException());
                        return;
                    }
                    Timber.i("addFriendToUserContacts() is successful");
                });
    }

}