package com.application.education.my.criminalintent;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CrimeLab {
    private static CrimeLab sCrimaLab;
    private List<Crime> mCrimes;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private CrimeLab (Context context){
        mCrimes=new ArrayList<>();

        for (int i=0;i<100; i++){
            Crime crime = null;
            try {
                crime = new Crime(context);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static CrimeLab getCrimaLab(Context context) {

        if(sCrimaLab==null){
            sCrimaLab=new CrimeLab(context);
        }
        return sCrimaLab;
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime (UUID id){
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id))
                return crime;
        }
        return null;
    }
}
