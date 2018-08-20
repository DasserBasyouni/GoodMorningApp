package com.tech.futureteric.backend.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.tech.futureteric.backend.R;

import static com.tech.futureteric.sharedcomponents.utils.AnimationUtils.startActivityWithAnimation;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.iconicsButton_phone_number).setOnClickListener(v ->
                startActivityWithAnimation(
                        new Intent(SignUpActivity.this, PhoneLoginActivity.class), this));
    }
}
