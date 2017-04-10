package com.application.education.my.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.application.education.my.criminalintent.database.CrimeBDSchema.CrimeTable;

/**
 * Created by magaz on 14.03.2017.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String DATABASE_NAME="crimebase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public CrimeBaseHelper(Context context, String dbName) {
        super(context, dbName, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("create table " + CrimeTable.NAME +
                "("+
                "_id integer primary key autoincrement, "+
                CrimeTable.Columns.UUID +", "+
                CrimeTable.Columns.TITLE +", "+
                CrimeTable.Columns.DATE +", "+
                CrimeTable.Columns.SOLVED+", "+
                CrimeTable.Columns.SUSPECT +", "+
                CrimeTable.Columns.ADDRESS_BOOK_ID+
                ")"
        );
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
