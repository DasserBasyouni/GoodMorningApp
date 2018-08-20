package com.tech.futureteric.backend.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

// TODO change to be Suggested friend and create a friend modle diff that this (with more details)
public class Friend implements Parcelable {

    private Bitmap profilePhoto;
    private String name, uid, token;
    private boolean isThisCurrentUser;

    public Friend() {}

    public Friend(Bitmap profilePhoto, String name, boolean isThisCurrentUser, String uid, String token) {
        this.uid = uid;
        this.profilePhoto = profilePhoto;
        this.name = name;
        this.token = token;
        this.isThisCurrentUser = isThisCurrentUser;
    }

    public Bitmap getProfilePhoto() {
        return profilePhoto;
    }

    public String getName() {
        return name;
    }

    @Exclude
    public boolean isThisCurrentUser() {
        return isThisCurrentUser;
    }

    public String getUid() {
        return uid;
    }

    public String getToken() {
        return token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.profilePhoto, flags);
        dest.writeString(this.name);
        dest.writeString(this.uid);
        dest.writeByte(this.isThisCurrentUser ? (byte) 1 : (byte) 0);
    }

    private Friend(Parcel in) {
        this.profilePhoto = in.readParcelable(Bitmap.class.getClassLoader());
        this.name = in.readString();
        this.uid = in.readString();
        this.isThisCurrentUser = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel source) {
            return new Friend(source);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}
