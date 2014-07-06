package com.github.mwolfe.euler;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwolfe on 7/6/14.
 */
public class Result implements Parcelable {

    public final String answer;
    public final int benchmarkTimeMs;

    public Result(String answer, int timestamp) {
        this.answer = answer;
        this.benchmarkTimeMs = timestamp;
    }

    public Result(Parcel in) {
        this.answer = in.readString();
        this.benchmarkTimeMs = in.readInt();
    }

    public static final Parcelable.Creator<Result> CREATOR
            = new Parcelable.Creator<Result>() {
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(answer);
        parcel.writeInt(benchmarkTimeMs);
    }
}
