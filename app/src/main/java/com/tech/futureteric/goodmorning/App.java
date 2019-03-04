package com.tech.futureteric.goodmorning;

import com.tech.futureteric.sharedcomponents.BuildConfig;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;
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
