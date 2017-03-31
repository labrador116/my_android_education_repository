package com.application.education.my.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.application.education.my.criminalintent.database.CrimeBDSchema.CrimeTable;
import com.application.education.my.criminalintent.database.CrimeBaseHelper;
import com.application.education.my.criminalintent.database.CrimeCursorWrapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CrimeLab {
    private static CrimeLab sCrimaLab;
    private  Context mContext;
    private SQLiteDatabase mDatabase;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private CrimeLab (Context context){
        mContext=context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static CrimeLab getCrimaLab(Context context) {

        if(sCrimaLab==null){
            sCrimaLab=new CrimeLab(context);
        }
        return sCrimaLab;
    }

    private  static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();

        values.put(CrimeTable.Columns.UUID, crime.getId().toString());
        values.put(CrimeTable.Columns.TITLE,crime.getTitle());
        values.put(CrimeTable.Columns.DATE,crime.getDate().getTime());
        values.put(CrimeTable.Columns.SOLVED,crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Columns.SUSPECT, crime.getSuspect());
        return values;
    }

    private CrimeCursorWrapper queryCrimes (String whereClause, String[] whereArgs){
        Cursor cursor= mDatabase.query(
            CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    public void addCrime(Crime crime){
        ContentValues values = getContentValues(crime);
        mDatabase.insert(CrimeTable.NAME,null,values);
    }
    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Columns.UUID + "= ?",
                new String[]{uuidString});
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public List<Crime> getCrimes(){
       List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursorWrapper = queryCrimes(null,null);

        try {
            cursorWrapper.moveToFirst();

            while(!cursorWrapper.isAfterLast()){
                try {
                    crimes.add(cursorWrapper.getCrime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return crimes;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Crime getCrime (UUID id){

        CrimeCursorWrapper cursor = queryCrimes(CrimeTable.Columns.UUID +"= ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount()==0){
                return null;
            }

        cursor.moveToFirst();
         return cursor.getCrime();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return null;
    }

    public void deleteCrime (Crime crime){
        mDatabase.delete(
                CrimeTable.NAME,
                CrimeTable.Columns.UUID +" = ?",
                new String[]{crime.getId().toString()}
        );
    }
}
