package com.tech.futureteric.backend.utils;

import android.app.Activity;
import android.content.Intent;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tech.futureteric.backend.R;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_OK;
import static com.tech.futureteric.backend.Constants.RC_SIGN_IN;

public class AppAuthUtils {

    private static FirebaseAuth.AuthStateListener authStateListener;

    // TODO Q can we use this logic in cloud function ?
    public static void listenToAuthStateAndReturnState(String token){
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            IdlingDataModel.getInstance().setData(token, DataType.AUTH_USER_LOGGED_IN);
        else{
            authStateListener = firebaseAuth -> {
                if (firebaseAuth.getCurrentUser() != null)
                    IdlingDataModel.getInstance().setData(token, DataType.AUTH_USER_LOGGED_IN);
            };
            FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
        }
    }

    public static void removeAuthStateListener(){
        if (authStateListener != null)
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
    }

    public static void verifyPhoneNumber(Activity activity, String phoneNumber,
                                  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,            // Phone number to verify
                60,                  // Timeout duration
                TimeUnit.SECONDS,       // Unit of timeout
                activity,               // Activity (for callback binding)
                mCallbacks);

    }

    public static void launchSignInActivity(Activity activity){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());

        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .build(),
                RC_SIGN_IN);
        }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

}