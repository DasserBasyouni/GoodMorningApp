package com.tech.futureteric.sharedcomponents.utils;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

public class PermissionUtils {

    // TODO handle denying permissionn
    public static void requestWriteToExternalStoragePermission(Activity activity, Bitmap bitmap) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        IdlingDataModel.getInstance().setData(bitmap, DataType.PERMISSION_STATUS_WRITE_TO_EXTERNAL_STORAGE);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        //IdlingDataModel.getInstance().setData(null, PERMISSION_STATUS_WRITE_TO_EXTERNAL_STORAGE);
                        PermissionListener dialogPermissionListener =
                                DialogOnDeniedPermissionListener.Builder
                                        .withContext(activity)
                                        .withTitle("Write to external storage permission")
                                        .withMessage("Write to external storage permission is needed to save backgrounds pictures")
                                        .withButtonText(android.R.string.ok)
                                        //.withIcon(R.mipmap.my_icon)
                                        .build();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();


    }
}
