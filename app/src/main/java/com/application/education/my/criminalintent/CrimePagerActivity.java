package com.application.education.my.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks {

    private static final String EXTRA_CRIME_ID =
            "com.application.education.my.criminalintent.CrimeActivity.crime_id";
    public static final String EXTRA_CRIME_POSITION =
            "com.application.education.my.criminalintent.CrimeActivity.position";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeID, int position){
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeID);
        intent.putExtra(EXTRA_CRIME_POSITION,position);
        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.getCrimaLab(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return CrimeFragment.newInstance(mCrimes
                        .get(position)
                        .getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mViewPager.setCurrentItem(getIntent().getIntExtra(EXTRA_CRIME_POSITION,0));
    }

    @Override
    protected void onStart() {
        super.onStart();
            int position = getIntent().getIntExtra(EXTRA_CRIME_POSITION,0);
            Intent intent = new Intent();
            intent.putExtra(EXTRA_CRIME_POSITION, position);
            setResult(RESULT_OK,intent);
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
