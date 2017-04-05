package com.application.education.my.criminalintent.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import com.application.education.my.criminalintent.fragments.CrimeFragment;
import com.application.education.my.criminalintent.fragments.CrimeListFragment;
import com.application.education.my.criminalintent.R;
import com.application.education.my.criminalintent.model.Crime;

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
        listFragment.updateUI(crime.getPosition());
    }
}
