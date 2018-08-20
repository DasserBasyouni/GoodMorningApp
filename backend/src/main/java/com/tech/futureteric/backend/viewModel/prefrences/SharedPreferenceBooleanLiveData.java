package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public final class SharedPreferenceBooleanLiveData extends SharedPreferenceLiveData {
    @NotNull
    public Boolean getValueFromPreferences(@NotNull String key, boolean defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.getSharedPrefs().getBoolean(key, defValue);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object getValueFromPreferences(String var1, Object var2) {
        return this.getValueFromPreferences(var1, var2);
    }

    public SharedPreferenceBooleanLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, boolean defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        super(sharedPrefs, key, defValue);
    }
}