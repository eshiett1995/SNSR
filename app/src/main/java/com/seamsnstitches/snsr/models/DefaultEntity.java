package com.seamsnstitches.snsr.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DefaultEntity implements Parcelable {

    public static final Creator<DefaultEntity> CREATOR = new Creator<DefaultEntity>() {
        @Override
        public DefaultEntity createFromParcel(Parcel in) {
            return new DefaultEntity(in);
        }

        @Override
        public DefaultEntity[] newArray(int size) {
            return new DefaultEntity[size];
        }
    };
    @SerializedName("id")
    private long id;
    //SerializedName("dateCreated")
    //private Date dateCreated;

    public DefaultEntity() {

    }

    protected DefaultEntity(Parcel in) {
        id = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Date getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated() {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//
//        try {
//
//            dateCreated = dateFormat.parse(dateFormat.format(new Date()));
//
//
//
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
}

