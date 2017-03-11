package com.application.education.my.criminalintent;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CrimeLab {
    private static CrimeLab sCrimaLab;
    private List<Crime> mCrimes;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private CrimeLab (Context context){
        mCrimes=new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static CrimeLab getCrimaLab(Context context) {

        if(sCrimaLab==null){
            sCrimaLab=new CrimeLab(context);
        }
        return sCrimaLab;
    }

    public void addCrime(Crime crime){
        mCrimes.add(crime);
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

    public void deleteCrime (Crime crime){
        mCrimes.remove(crime);
    }
}
