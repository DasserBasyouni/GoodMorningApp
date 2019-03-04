package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

public final class SharedPreferenceStringLiveData extends SharedPreferenceLiveData {

    @NotNull
    private String getValueFromPreferences(@NotNull String key, @NotNull String defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        String var10000 = this.getSharedPrefs().getString(key, defValue);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "sharedPrefs.getString(key, defValue)");
        return var10000;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object getValueFromPreferences(@NonNull String var1, Object var2) {
        return this.getValueFromPreferences(var1, (String)var2);
    }

    public SharedPreferenceStringLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, @NotNull String defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        //Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        super(sharedPrefs, key, defValue);
    }
}