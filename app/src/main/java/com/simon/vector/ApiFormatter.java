package com.simon.vector;

import android.os.Parcel;
import android.os.Parcelable;

public class ApiFormatter implements Parcelable {

    public final static String ACCESS_TOKEN = "access_token=4e5ecd9c1fda50d086f911abde31d445553e0d6bdda1909f1d102d9f39f3a1b3";
    public final static String Prefix = "https://api.dribbble.com/v1/";

    private StringBuilder stringBuilder = new StringBuilder();
    private int length = 0;

    public ApiFormatter() {
        stringBuilder.append(Prefix);
    }

    public ApiFormatter addQuery(String str) {
        stringBuilder.append(str);
        return this;
    }

    public ApiFormatter addOption(String str) {
        length++;

        if (length == 1) {
            stringBuilder.append("?");
        } else {
            stringBuilder.append("&");
        }

        stringBuilder.append(str);

        return this;
    }

    public ApiFormatter changeOption(String option, String newValue, String oldValue) {
        String result = stringBuilder.toString();
        stringBuilder = new StringBuilder().append(result.replace(option + "=" + oldValue, option + "=" + newValue));
        return this;
    }

    public String toString() {
        return stringBuilder.toString();
    }


    protected ApiFormatter(Parcel in) {
        length = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(length);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ApiFormatter> CREATOR = new Parcelable.Creator<ApiFormatter>() {
        @Override
        public ApiFormatter createFromParcel(Parcel in) {
            return new ApiFormatter(in);
        }

        @Override
        public ApiFormatter[] newArray(int size) {
            return new ApiFormatter[size];
        }
    };
}