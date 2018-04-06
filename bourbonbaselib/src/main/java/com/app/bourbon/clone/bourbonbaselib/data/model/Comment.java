package com.app.bourbon.clone.bourbonbaselib.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 06/04/18.
 */

public class Comment implements Parcelable {

    public int id;
    public String body;
    public User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected Comment(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(this.id);
        parcel.writeString(this.body);
        parcel.writeParcelable(this.user, i);
    }

    protected Comment(Parcel in) {
        this.id = in.readInt();
        this.body = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
