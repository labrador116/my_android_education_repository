package com.application.education.my.criminalintent.DataBaseTests;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.application.education.my.criminalintent.database.CrimeBDSchema;
import com.application.education.my.criminalintent.database.CrimeBDSchema.CrimeTable.Columns;
import com.application.education.my.criminalintent.database.CrimeBaseHelper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class CrimeBaseHelperTest {
    private static final String DB_NAME = "crime_test.db";
    private CrimeBaseHelper mCrimeHelper;


    public void testCreation(){
        mCrimeHelper = new CrimeBaseHelper(InstrumentationRegistry.getContext(),DB_NAME);
        SQLiteDatabase db = mCrimeHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(CrimeBDSchema.CrimeTable.NAME, null, null, null, null, null, null);
            assertThat(cursor, notNullValue());

            for (String column : new String[]{Columns.SUSPECT, Columns.ADDRESS_BOOK_ID, Columns.DATE, Columns.SOLVED, Columns.TITLE, Columns.UUID}){
                int columnIndex = cursor.getColumnIndex(column);
                assertThat(columnIndex, not(-1));
            }
        }
        finally {
            if (cursor!=null){
                cursor.close();
            }
            db.close();
        }
    }

    @After
    public void tearDown() {
        InstrumentationRegistry.getContext().deleteDatabase(DB_NAME);
    }
}
