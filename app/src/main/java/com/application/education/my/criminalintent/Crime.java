package com.application.education.my.criminalintent;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Crime(UUID id) throws ParseException {
        mId = id;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        mDate=df.parse(formattedDate);
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}


