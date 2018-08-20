package com.tech.futureteric.goodmorning;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.tech.futureteric.sharedcomponents.BuildConfig;

import timber.log.Timber;

import static com.tech.futureteric.backend.utils.Utils.enablePicassoCaching;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new NotLoggingTree());

        enablePicassoCaching(this);
    }

    private static class NotLoggingTree extends Timber.Tree {
        @Override
        protected void log(final int priority, final String tag, @NonNull final String message, final Throwable throwable) {}
    }
}
