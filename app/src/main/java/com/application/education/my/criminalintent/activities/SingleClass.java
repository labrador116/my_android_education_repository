package com.application.education.my.criminalintent.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.application.education.my.criminalintent.fragments.DatePickerFragment;

import java.util.Date;

public class SingleClass extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext, Date date){
        Intent intent = new Intent(packageContext, SingleClass.class);
        intent.putExtra(DatePickerFragment.EXTRA_DATE,date);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        Date date =(Date) getIntent().getSerializableExtra("date");
        return DatePickerFragment.newInstance(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
    }
}

