package com.tech.futureteric.backend.viewModel.prefrences;

import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public abstract class SharedPreferenceLiveData extends LiveData {
    private final SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    @NotNull
    private final SharedPreferences sharedPrefs;
    @NotNull
    private final String key;
    private final Object defValue;

    public abstract Object getValueFromPreferences(@NotNull String var1, Object var2);

    protected void onActive() {
        super.onActive();
        this.setValue(this.getValueFromPreferences(this.key, this.defValue));
        this.sharedPrefs.registerOnSharedPreferenceChangeListener(this.preferenceChangeListener);
    }

    protected void onInactive() {
        this.sharedPrefs.unregisterOnSharedPreferenceChangeListener(this.preferenceChangeListener);
        super.onInactive();
    }

    @NotNull
    public final SharedPreferences getSharedPrefs() {
        return this.sharedPrefs;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public final Object getDefValue() {
        return this.defValue;
    }

    public SharedPreferenceLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, Object defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        super();
        this.sharedPrefs = sharedPrefs;
        this.key = key;
        this.defValue = defValue;
        this.preferenceChangeListener = (sharedPreferences, key1) -> {
            if (Intrinsics.areEqual(key1, SharedPreferenceLiveData.this.getKey())) {
                SharedPreferenceLiveData.this.setValue(SharedPreferenceLiveData.this.getValueFromPreferences(key1, SharedPreferenceLiveData.this.getDefValue()));
            }

        };
    }
}