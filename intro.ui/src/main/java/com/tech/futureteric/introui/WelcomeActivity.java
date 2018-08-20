package com.tech.futureteric.introui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.tech.futureteric.backend.utils.AppAuthUtils;

import static com.tech.futureteric.backend.utils.AppAuthUtils.launchSignInActivity;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.isUserLoggedIn;
import static com.tech.futureteric.sharedcomponents.utils.AppUtils.isItOnDoubleBackButton;

public class WelcomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isUserLoggedIn())
            finish();

        setContentView(R.layout.activity_welcome);

        ((TextView) findViewById(R.id.textView)).setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));

        findViewById(R.id.button_sign_in).setOnClickListener(v ->
                launchSignInActivity(WelcomeActivity.this)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO search for more info about interface vs model vs this way
        AppAuthUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (isItOnDoubleBackButton(getBaseContext()))
            super.onBackPressed();
    }
}