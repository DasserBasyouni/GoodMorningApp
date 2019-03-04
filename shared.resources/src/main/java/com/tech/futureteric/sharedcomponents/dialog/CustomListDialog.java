package com.tech.futureteric.sharedcomponents.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import androidx.recyclerview.widget.RecyclerView;

public class CustomListDialog {

    private MaterialDialog progressDialog;

    public void showDialog(Context context, String title, RecyclerView.Adapter adapter,
                           RecyclerView.LayoutManager layoutManager) {

        progressDialog = new MaterialDialog.Builder(context)
                .title(title)
                .adapter(adapter, layoutManager)
                .positiveText("Done")
                .onPositive((dialog, which) -> dialog.dismiss())
                .show();
    }

    public void dismissDialog(){
        progressDialog.dismiss();
    }

    public void setNewTitle(String newTitle){
        progressDialog.setTitle(newTitle);
    }
}
