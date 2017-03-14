package com.application.education.my.criminalintent.database;


import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.application.education.my.criminalintent.Crime;
import com.application.education.my.criminalintent.database.CrimeBDSchema.CrimeTable.Columns;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Crime getCrime() throws ParseException {
        String uuidString = getString(getColumnIndex(Columns.UUID));
        String title = getString(getColumnIndex(Columns.TITLE));
        long date = getLong(getColumnIndex((Columns.DATE)));
        int isSolved = getInt(getColumnIndex(Columns.SOLVED));

        Crime crime =new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved!=0);

        return crime;
    }
}
