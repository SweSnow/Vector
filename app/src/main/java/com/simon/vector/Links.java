package com.simon.vector;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {
    private String twitter;
    private String web;

    public String getTwitter(){
        return this.twitter;
    }
    public void setTwitter(String twitter){
        this.twitter = twitter;
    }
    public String getWeb(){
        return this.web;
    }
    public void setWeb(String web){
        this.web = web;
    }

    public Links() {

    }

    protected Links(Parcel in) {
        twitter = in.readString();
        web = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(twitter);
        dest.writeString(web);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
}