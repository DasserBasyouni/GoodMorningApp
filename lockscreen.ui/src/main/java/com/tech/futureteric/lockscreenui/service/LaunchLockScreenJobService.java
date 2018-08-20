package com.tech.futureteric.lockscreenui.service;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.tech.futureteric.lockscreenui.ui.LockScreenBuilder;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import timber.log.Timber;

import static com.tech.futureteric.backend.utils.AppDatabaseUtils.getAllMessages;
import static com.tech.futureteric.backend.utils.MorningImageUtils.returnIdlingDataWhenImageIsCached;

public class LaunchLockScreenJobService extends JobService implements IdlingDataModel.OnDataReady{


    @Override
    public boolean onStartJob(JobParameters job) {
        IdlingDataModel.getInstance().setListener(this);
        returnIdlingDataWhenImageIsCached(job);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }

    @Override
    public void dataAreReceived() {
        int dataType = IdlingDataModel.getInstance().getDataType();
        JobParameters job = (JobParameters) IdlingDataModel.getInstance().getData();
        Timber.i("dataType: %s", dataType);

        if (dataType == DataType.LOCK_SCREEN_MORNING_IMAGE_IS_CACHED) {
            LockScreenBuilder.buildThenShowLockScreen(this, getAllMessages(this));
            jobFinished(job, false);

        } else if (dataType == DataType.LOCK_SCREEN_MORNING_IMAGE_ERROR){
            // TODO may display error and handle reschedule, also can use default background instead of fetching
            jobFinished(job, true);

        }
    }

}