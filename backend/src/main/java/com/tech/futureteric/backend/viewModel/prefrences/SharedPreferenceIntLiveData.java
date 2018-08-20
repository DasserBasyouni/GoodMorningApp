package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public final class SharedPreferenceIntLiveData extends SharedPreferenceLiveData {
    @NotNull
    public Integer getValueFromPreferences(@NotNull String key, int defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.getSharedPrefs().getInt(key, defValue);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object getValueFromPreferences(String var1, Object var2) {
        return this.getValueFromPreferences(var1, ((Number)var2).intValue());
    }

    public SharedPreferenceIntLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, int defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        super(sharedPrefs, key, defValue);
    }
}