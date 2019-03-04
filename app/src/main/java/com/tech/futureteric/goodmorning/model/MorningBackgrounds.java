package com.tech.futureteric.goodmorning.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class MorningBackgrounds {

    private List<Results> results;
    @Ignore
    private int total_pages;

    public List<Results> getResults() {
        return results;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public static class Results implements Parcelable {
        private boolean sponsored;
        @PrimaryKey
        private String id;

        public boolean isSponsored() {
            return sponsored;
        }

        public String getId() {
            return id;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.sponsored ? (byte) 1 : (byte) 0);
            dest.writeString(this.id);
        }

        public Results() {
        }

        protected Results(Parcel in) {
            this.sponsored = in.readByte() != 0;
            this.id = in.readString();
        }

        public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
            @Override
            public Results createFromParcel(Parcel source) {
                return new Results(source);
            }

            @Override
            public Results[] newArray(int size) {
                return new Results[size];
            }
        };
    }

}
