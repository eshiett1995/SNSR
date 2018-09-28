package com.seamsnstitches.snsr.models;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DefaultEntity implements Serializable {

    private long id;

    private Date dateCreated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {

            dateCreated = dateFormat.parse(dateFormat.format(new Date()));



        } catch (ParseException e) {

            e.printStackTrace();

        }

    }
}

