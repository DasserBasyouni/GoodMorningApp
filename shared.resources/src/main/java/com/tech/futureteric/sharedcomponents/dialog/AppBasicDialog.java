package com.tech.futureteric.sharedcomponents.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tech.futureteric.sharedcomponents.model.DialogOnPositiveModel;

public class AppBasicDialog {

    public void showFindConnectionsDialog(Context context, String title, String button,
                                          DialogOnPositiveModel.OnPositive onPositive) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title);

        if (!button.equals("")){
            DialogOnPositiveModel.getInstance().setListener(onPositive);
            builder.positiveText(button).onPositive(
                    (dialog, which) -> DialogOnPositiveModel.getInstance().perform())
                    .canceledOnTouchOutside(false)
                    .cancelable(false);
        } else
            builder.positiveText("Ok").onPositive((dialog, which) -> dialog.dismiss());

        builder.show();
    }

}

