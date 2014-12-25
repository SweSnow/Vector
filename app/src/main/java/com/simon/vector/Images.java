package com.simon.vector;

import android.os.Parcel;
import android.os.Parcelable;

public class Images implements Parcelable {
    private String hidpi;
    private String normal;
    private String teaser;

    public String getHidpi(){
        return this.hidpi;
    }
    public void setHidpi(String hidpi){
        this.hidpi = hidpi;
    }
    public String getNormal(){
        return this.normal;
    }
    public void setNormal(String normal){
        this.normal = normal;
    }
    public String getTeaser(){
        return this.teaser;
    }
    public void setTeaser(String teaser){
        this.teaser = teaser;
    }

    protected Images(Parcel in) {
        hidpi = in.readString();
        normal = in.readString();
        teaser = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hidpi);
        dest.writeString(normal);
        dest.writeString(teaser);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}