package com.tech.futureteric.lockscreenui.ui;

import android.content.Context;
import android.util.Log;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import com.tech.futureteric.lockscreenui.R;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import androidx.fragment.app.FragmentManager;

public class DateAndTimePickerDialog {

    public static void showDialog(Context context, FragmentManager fragmentManager){

        SwitchDateTimeDialogFragment dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                context.getString(R.string.label_datetime_dialog),
                context.getString(R.string.label_send),
                context.getString(android.R.string.cancel)
        );

        dateTimeFragment.setTimeZone(TimeZone.getDefault());

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        dateTimeFragment.setMinimumDateTime(new GregorianCalendar(year, month, dayOfMonth).getTime());

        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                Log.e("Z_", "date==== " + date);
                IdlingDataModel.getInstance().setData(date, DataType.DIALOG_ON_POSITIVE);
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Do nothing
            }

            @Override
            public void onNeutralButtonClick(Date date) {
                // Optional if neutral button does'nt exists
                Log.e("Z_", "date==22== " + date);
            }
        });


        dateTimeFragment.startAtCalendarView();
        dateTimeFragment.setDefaultDateTime(new GregorianCalendar(year, month,
                dayOfMonth, 8, 0).getTime());
        dateTimeFragment.show(fragmentManager, "TAG_DATETIME_FRAGMENT");
    }
}
