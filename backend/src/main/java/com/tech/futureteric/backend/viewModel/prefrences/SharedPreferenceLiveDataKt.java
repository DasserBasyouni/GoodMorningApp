package com.tech.futureteric.backend.viewModel.prefrences;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class SharedPreferenceLiveDataKt {
    @NotNull
    public static final SharedPreferenceLiveData intLiveData(@NotNull SharedPreferences $receiver, @NotNull String key, int defValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new SharedPreferenceIntLiveData($receiver, key, defValue);
    }

    @NotNull
    public static final SharedPreferenceLiveData stringLiveData(@NotNull SharedPreferences $receiver, @NotNull String key, @NotNull String defValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        return new SharedPreferenceStringLiveData($receiver, key, defValue);
    }

    @NotNull
    public static final SharedPreferenceLiveData booleanLiveData(@NotNull SharedPreferences $receiver, @NotNull String key, boolean defValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new SharedPreferenceBooleanLiveData($receiver, key, defValue);
    }

    @NotNull
    public static final SharedPreferenceLiveData floatLiveData(@NotNull SharedPreferences $receiver, @NotNull String key, float defValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new SharedPreferenceFloatLiveData($receiver, key, defValue);
    }

    @NotNull
    public static final SharedPreferenceLiveData longLiveData(@NotNull SharedPreferences $receiver, @NotNull String key, long defValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new SharedPreferenceLongLiveData($receiver, key, defValue);
    }

    @NotNull
    public static final SharedPreferenceLiveData stringSetLiveData(@NotNull SharedPreferences $receiver, @NotNull String key, @NotNull Set defValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        return new SharedPreferenceStringSetLiveData($receiver, key, defValue);
    }
}
