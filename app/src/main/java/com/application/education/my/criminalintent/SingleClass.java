package com.application.education.my.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

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

