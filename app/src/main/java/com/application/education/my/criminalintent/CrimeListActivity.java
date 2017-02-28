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
        int position= data.getIntExtra("com.application.education.my.criminalintent.CrimeActivity.position",0);

        Intent intent =new Intent();
        intent.putExtra("com.application.education.my.criminalintent.CrimeActivity.position", position);
        setIntent(intent);
    }
}
