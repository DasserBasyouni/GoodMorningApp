package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public final class SharedPreferenceFloatLiveData extends SharedPreferenceLiveData {
    @NotNull
    public Float getValueFromPreferences(@NotNull String key, float defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.getSharedPrefs().getFloat(key, defValue);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object getValueFromPreferences(String var1, Object var2) {
        return this.getValueFromPreferences(var1, ((Number)var2).floatValue());
    }

    public SharedPreferenceFloatLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, float defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        super(sharedPrefs, key, defValue);
    }
}