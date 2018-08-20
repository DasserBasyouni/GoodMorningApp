package com.tech.futureteric.lockscreenui.utils;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.tech.futureteric.lockscreenui.service.LaunchLockScreenJobService;

import static com.tech.futureteric.backend.Constants.TAG_LOCK_SCREEN_JOB_TAG;

public class LockScreenUtils {

    public static void scheduelLockScreenAtTime(Context context, String timestamp) {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

        Log.e("Z_", "bundle2= " + timestamp);
        // TODO handle if the user input more than 18 hours
        int windowStart = (int) (calculateTimeOfMsg(timestamp));

        dispatcher.mustSchedule(dispatcher.newJobBuilder()
                .setService(LaunchLockScreenJobService.class)
                .setTag(TAG_LOCK_SCREEN_JOB_TAG)
                .setRecurring(false)
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setTrigger(Trigger.executionWindow(windowStart-5, windowStart+25))
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build());
    }

    // TODO take in confederations nanoTime is better in time zones
    private static long calculateTimeOfMsg(String msgTs) {
        return Math.abs(Long.parseLong(msgTs) - (System.currentTimeMillis()/1000));
    }

}
