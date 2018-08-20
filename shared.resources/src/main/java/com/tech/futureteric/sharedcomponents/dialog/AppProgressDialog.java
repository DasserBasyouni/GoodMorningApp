package com.tech.futureteric.sharedcomponents.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

public class AppProgressDialog {

    private MaterialDialog progressDialog;

    public void showProgressDialog(Context context, String title, String content) {
        progressDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void dismissDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void setNewTitle(String newTitle){
        progressDialog.setTitle(newTitle);
    }
}

