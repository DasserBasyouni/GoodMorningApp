package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public final class SharedPreferenceLongLiveData extends SharedPreferenceLiveData {
    @NotNull
    public Long getValueFromPreferences(@NotNull String key, long defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.getSharedPrefs().getLong(key, defValue);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object getValueFromPreferences(String var1, Object var2) {
        return this.getValueFromPreferences(var1, ((Number)var2).longValue());
    }

    public SharedPreferenceLongLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, long defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        super(sharedPrefs, key, defValue);
    }
}