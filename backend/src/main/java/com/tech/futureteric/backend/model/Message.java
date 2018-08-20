package com.tech.futureteric.backend.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class Message implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "msg_id")
    public String mMsgId;

    @ColumnInfo(name = "time_stamp")
    public String mTimestamp;

    @ColumnInfo(name = "sender_uid")
    public String mSenderUid;

    @ColumnInfo(name = "msg")
    public String mMsg;

    @ColumnInfo(name = "sender")
    public String mSenderName;

    public Message() {}

    public Message(String senderUid, String msg, String sender, String timestamp, String msgId) {
        mMsgId = msgId;
        mSenderUid = senderUid;
        mMsg = msg;
        mSenderName = sender;
        mTimestamp = timestamp;
    }

    public Message(String senderUid, String msg, String sender, String timestamp) {
        mSenderUid = senderUid;
        mMsg = msg;
        mSenderName = sender;
        mTimestamp = timestamp;
    }

    public String getMsg() {
        return mMsg;
    }

    public String getSenderName() {
        return mSenderName;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public String getmSenderUid() {
        return mSenderUid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMsgId);
        dest.writeString(this.mTimestamp);
        dest.writeString(this.mSenderUid);
        dest.writeString(this.mMsg);
        dest.writeString(this.mSenderName);
    }

    protected Message(Parcel in) {
        this.mMsgId = in.readString();
        this.mTimestamp = in.readString();
        this.mSenderUid = in.readString();
        this.mMsg = in.readString();
        this.mSenderName = in.readString();
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
