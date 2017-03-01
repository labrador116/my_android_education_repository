package com.application.education.my.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int position= data.getIntExtra(CrimePagerActivity.EXTRA_CRIME_POSITION,0);

        Intent intent =new Intent();
        intent.putExtra(CrimePagerActivity.EXTRA_CRIME_POSITION, position);
        setIntent(intent);
    }
}
