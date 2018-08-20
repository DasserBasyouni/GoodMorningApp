package com.tech.futureteric.lockscreenui.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tech.futureteric.backend.model.Message;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.Map;

import timber.log.Timber;

import static com.tech.futureteric.backend.utils.AppAuthUtils.listenToAuthStateAndReturnState;
import static com.tech.futureteric.backend.utils.AppAuthUtils.removeAuthStateListener;
import static com.tech.futureteric.backend.utils.AppDatabaseUtils.addMessage;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.addUserFcmTokenToDatabase;
import static com.tech.futureteric.lockscreenui.utils.LockScreenUtils.scheduelLockScreenAtTime;

public class AppFirebaseMessagingService extends FirebaseMessagingService implements IdlingDataModel.OnDataReady {

    /** Called when message is received.
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging. */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Timber.d("From: %s", remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {

            Map<String, String> data = remoteMessage.getData();
            Timber.d("Message data payload: %s", data);

            addMessage(this, getFormattedMessage(data));
            scheduelLockScreenAtTime(this, data.get("time"));
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Timber.d("Message Notification Body: %s", remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private Message getFormattedMessage(Map<String, String> data) {
        return new Message(data.get("uid"),
                data.get("msg"),
                data.get("senderName"),
                data.get("time"),
                data.get("msgId"));
    }

    /** Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.*/
    @Override
    public void onNewToken(String token) {
        Timber.d("Refreshed token: %s", token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        IdlingDataModel.getInstance().setListener(this);
        listenToAuthStateAndReturnState(token);
    }

    // TODO case of user open the app and then closed it does the token still there to do get to server ?!
    @Override
    public void dataAreReceived() {
        int dataType = IdlingDataModel.getInstance().getDataType();
        String token = (String) IdlingDataModel.getInstance().getData();

        Log.e("Z_t", "tt= " +  token);

        if (dataType == DataType.AUTH_USER_LOGGED_IN) {
            removeAuthStateListener();
            addUserFcmTokenToDatabase(token);
        }
    }
}