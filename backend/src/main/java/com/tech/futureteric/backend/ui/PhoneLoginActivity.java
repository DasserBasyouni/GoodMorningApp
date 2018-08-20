package com.tech.futureteric.backend.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.infideap.blockedittext.BlockEditText;
import com.lamudi.phonefield.PhoneInputLayout;
import com.tech.futureteric.backend.utils.AppAuthUtils;
import com.tech.futureteric.backend.R;

import static com.tech.futureteric.backend.utils.Utils.disableEditText;

public class PhoneLoginActivity extends AppCompatActivity {

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private  boolean changed = false;
    private boolean isReceivedCodeTimeIsActive;
    private EditText mDisplayedName_et;
    private PhoneInputLayout phoneInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        setupPhoneFieldView();
    }

    private void setupPhoneFieldView() {
        phoneInputLayout = findViewById(R.id.phone_input_layout);
        final Button send_btn = findViewById(R.id.button_send);

        phoneInputLayout.setHint(R.string.msg_phone_number_hint);

        // you can set the default country as follows
        phoneInputLayout.setDefaultCountry("EG");

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;

                if (phoneInputLayout.isValid())
                    phoneInputLayout.setError(null);
                else {
                    phoneInputLayout.setError(getString(R.string.msg_invalid_phone_number));
                    valid = false;
                }

                if (valid) {
                    Toast.makeText(PhoneLoginActivity.this,
                            R.string.msg_valid_phone_number, Toast.LENGTH_LONG).show();
                    launchPhoneNumberVerifier(phoneInputLayout.getPhoneNumber());
                }else
                    Toast.makeText(PhoneLoginActivity.this,
                            R.string.msg_invalid_phone_number, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void launchPhoneNumberVerifier(final String phoneNumber) {
        AppAuthUtils.verifyPhoneNumber(PhoneLoginActivity.this,
                phoneNumber, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Log.e("Z_", "case 1: " + phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Log.e("Z_", "case 2: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        isReceivedCodeTimeIsActive = true;
                        // The SMS verification code has been sent to the provided phone number, we
                        // now need to ask the user to enter the code and then construct a credential
                        // by combining the code with a verification ID.
                        Log.d("Z_TAG", "onCodeSent: " + verificationId);

                        disableEditTexts();

                        applyToConstraintSet(findViewById(R.id.constraintLayout),
                                R.layout.activity_phone_login_verify);
                        applyToConstraintSet(findViewById(R.id.constraintLayout2),
                                R.layout.activity_phone_login_verify_include);

                        // Save verification ID and resending token so we can use them later
                        mVerificationId = verificationId;
                        mResendToken = token;
                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String s) {
                        Log.e("Z_", "case 3: " + s);

                        isReceivedCodeTimeIsActive = false;

                        applyToConstraintSet(findViewById(R.id.constraintLayout),
                                R.layout.activity_phone_login);
                        applyToConstraintSet(findViewById(R.id.constraintLayout2),
                                R.layout.activity_phone_login_include);
                    }

                    private void disableEditTexts() {
                        mDisplayedName_et = ((TextInputLayout) findViewById(R.id.textInputLayout_name)).getEditText();
                        if (mDisplayedName_et != null)
                            disableEditText(mDisplayedName_et);
                        disableEditText(phoneInputLayout.getEditText());
                    }
                });
    }

    private void applyToConstraintSet(ConstraintLayout constraintLayout, int layoutResId) {

        ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.clone(constraintLayout);

        ConstraintSet constraintSet2 = new ConstraintSet();
        constraintSet2.clone(PhoneLoginActivity.this, layoutResId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(constraintLayout);

            Toast.makeText(this, "its done", Toast.LENGTH_SHORT).show();
        }

        constraintSet2.applyTo(constraintLayout);
        initDownTimerTextView();
    }

    private void initDownTimerTextView() {
        final TextView textView = findViewById(R.id.textView_downTimer);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText(getString(R.string.label_downTimer, millisUntilFinished/1000));

                if ((millisUntilFinished/1000) == 50) {
                    BlockEditText code_bet = findViewById(R.id.blockEditText_code);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code_bet.getText());
                    signInWithPhoneAuthCredential(credential);
                }
            }

            public void onFinish() { }
        }.start();
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Z_TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("Z_TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        if(!isReceivedCodeTimeIsActive)
            super.onBackPressed();
    }
}