package com.tech.futureteric.sharedcomponents.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;

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
