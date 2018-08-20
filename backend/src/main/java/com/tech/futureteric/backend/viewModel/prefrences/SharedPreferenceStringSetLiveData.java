package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class SharedPreferenceStringSetLiveData extends SharedPreferenceLiveData {
    @NotNull
    public Set getValueFromPreferences(@NotNull String key, @NotNull Set defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        Set var10000 = this.getSharedPrefs().getStringSet(key, defValue);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "sharedPrefs.getStringSet(key, defValue)");
        return var10000;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object getValueFromPreferences(String var1, Object var2) {
        return this.getValueFromPreferences(var1, (Set)var2);
    }

    public SharedPreferenceStringSetLiveData(@NotNull SharedPreferences sharedPrefs, @NotNull String key, @NotNull Set defValue) {
        //Intrinsics.checkParameterIsNotNull(sharedPrefs, "sharedPrefs");
        //Intrinsics.checkParameterIsNotNull(key, "key");
        //Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        super(sharedPrefs, key, defValue);
    }
}