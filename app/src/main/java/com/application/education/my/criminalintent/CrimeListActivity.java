package com.application.education.my.criminalintent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int position= data.getIntExtra(CrimePagerActivity.EXTRA_CRIME_POSITION,0);

        Intent intent =new Intent();
        intent.putExtra(CrimePagerActivity.EXTRA_CRIME_POSITION, position);
        setIntent(intent);
    }

    @Override
    public void onCrimeSelected(Crime crime, int position) {
        if (findViewById(R.id.detail_fragment_container)==null){
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId(), position);
            startActivity(intent);
        } else{
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, newDetail).commit();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
