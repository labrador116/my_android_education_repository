package com.application.education.my.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity{

    private static final String EXTRA_CRIME_ID =
            "com.application.education.my.criminalintent.CrimeActivity.crime_id";
    public static final String EXTRA_CRIME_POSITION =
            "com.application.education.my.criminalintent.CrimeActivity.position";

    public static Intent newIntent(Context packageContext, UUID crimeID, int position){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeID);
        intent.putExtra(EXTRA_CRIME_POSITION,position);
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        int position = getIntent().getIntExtra(EXTRA_CRIME_POSITION,0);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_CRIME_POSITION, position);
        setResult(RESULT_OK,intent);
    }


    protected Fragment createFragment() {
        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }

}
