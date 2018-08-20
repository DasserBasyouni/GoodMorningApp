package com.tech.futureteric.findconnections.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.tech.futureteric.backend.model.Friend;
import com.tech.futureteric.findconnections.R;
import com.tech.futureteric.findconnections.Utils;
import com.tech.futureteric.findconnections.adapter.FoundFriendsAdapter;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.dialog.AppBasicDialog;
import com.tech.futureteric.sharedcomponents.dialog.AppProgressDialog;
import com.tech.futureteric.sharedcomponents.dialog.CustomListDialog;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.tech.futureteric.backend.utils.user.UserInfoUtils.isThisCurrentUser;
import static com.tech.futureteric.backend.utils.user.UsersSearchUtils.searchForUserByEmail;
import static com.tech.futureteric.backend.utils.user.UsersSearchUtils.searchForUserByPhone;
import static com.tech.futureteric.sharedcomponents.utils.IconUtils.getEmailDrawable;
import static com.tech.futureteric.sharedcomponents.utils.IconUtils.getPhoneDrawable;
import static com.tech.futureteric.sharedcomponents.utils.IconUtils.getSearchDrawable;

public class FindFriendsActivity extends AppCompatActivity implements IdlingDataModel.OnDataReady{

    private AppProgressDialog appProgressDialog;
    private String validInput;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_connections);
        setLayoutIconsAndSetupFab();
    }

    private void setLayoutIconsAndSetupFab() {
        ((MaterialTextField) findViewById(R.id.materialTextField_email))
                .getImage().setImageDrawable(getEmailDrawable(this));

        ((MaterialTextField) findViewById(R.id.materialTextField_phone))
                .getImage().setImageDrawable(getPhoneDrawable(this));

        FloatingActionButton search_fab = findViewById(R.id.fab_search);
        search_fab.setBackgroundDrawable(getSearchDrawable(this));
        search_fab.setOnClickListener(v -> {
            appProgressDialog = new AppProgressDialog();
            appProgressDialog.showProgressDialog(this, null, getString(R.string.msg_searching));
            checkThenSearchForInputs();
        });
    }

    // TODO handle if user insert more than one data input at once
    private void checkThenSearchForInputs() {
        IdlingDataModel.getInstance().setListener(this);

        EditText phone_et = findViewById(R.id.editText_phone);
        String input = phone_et.getText().toString();
        if (!input.isEmpty()){
            if (Utils.isValidPhoneNumber(input)){
                searchForUserByPhone(this, input);
                validInput = input;
            }
        }

        EditText email_et = findViewById(R.id.editText_email);
        input = email_et.getText().toString();
        if (!input.isEmpty()){
            if (Utils.isValidEmail(input)){
                searchForUserByEmail(this, input);
                validInput = input;
            }
        }
    }

    // TODO handle case of inputting umber and email in the same time
    // TODO handle inputting phone number with or without country code and numbers count does exceeds or not
    // TODO handle when user search for a friend that has just created his account
    @Override
    public void dataAreReceived() {
        appProgressDialog.dismissDialog();

        Map<String, Object> data = (Map<String, Object>) IdlingDataModel.getInstance().getData();
        int dataType = IdlingDataModel.getInstance().getDataType();

        if (dataType == DataType.NO_DATA_FOUND){
            new AppBasicDialog().showFindConnectionsDialog(this, "No Friend Found", "", null);
        } else if (dataType == DataType.DEFAULT){
            Log.e("Z_2", "data: " + data.toString());
            String uid = (String) data.get("uid");
            String token = (String) data.get("token");

            List<Friend> friendList = new ArrayList<>();
            friendList.add(new Friend(null, validInput, isThisCurrentUser(uid), uid, token));

            new CustomListDialog().showDialog(this,
                    getString(R.string.label_found_friend),
                    new FoundFriendsAdapter(this, friendList), null);
        } else if (dataType == DataType.FETCHING_GOT_EXCEPTION){
            // TODO display error and hide progress
        }
    }
}
