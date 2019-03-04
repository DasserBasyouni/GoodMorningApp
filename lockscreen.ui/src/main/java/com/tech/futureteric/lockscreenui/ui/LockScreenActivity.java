package com.tech.futureteric.lockscreenui.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.tech.futureteric.lockscreenui.R;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import timber.log.Timber;

import static com.tech.futureteric.backend.utils.MorningImageUtils.getDailyMorningImageBitmap;


public class LockScreenActivity extends AppCompatActivity implements IdlingDataModel.OnDataReady {

    private ConstraintLayout background_cl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        background_cl = findViewById(R.id.constraintLayout_background);
        IdlingDataModel.getInstance().setListener(this);
        getDailyMorningImageBitmap(this, true);

        Bundle bundle = getIntent().getExtras();

        getFragmentManager().beginTransaction().add(R.id.frameLayout_locksScreen_placeHolder,
                LockScreenFragment.newInstance(bundle), "LockScreenFragment_tag").commit();

        makeStatusAndNavBarFullTransparent();
    }


    @Override
    public void dataAreReceived() {
        Object data = IdlingDataModel.getInstance().getData();
        int dataType = IdlingDataModel.getInstance().getDataType();
        Timber.i("Received data type: %s", dataType);

        if (dataType == DataType.MORNING_IMAGE_CACHED_SUCCESS || dataType == DataType.MORNING_IMAGE_ONLINE_SUCCESS) {
            Drawable drawable = new BitmapDrawable(getResources(), (Bitmap) data);
            background_cl.setBackgroundDrawable(drawable);

        } else if (dataType == DataType.MORNING_IMAGE_CACHED_ERROR) {
            // TODO display error professionally
            getDailyMorningImageBitmap(this, false);

        } else if (dataType == DataType.MORNING_IMAGE_ONLINE_ERROR) {
            // TODO display error professionally

        }
    }

    private void makeStatusAndNavBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
        }
    }
}