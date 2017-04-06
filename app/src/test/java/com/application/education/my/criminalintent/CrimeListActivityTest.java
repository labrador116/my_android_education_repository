package com.application.education.my.criminalintent;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.application.education.my.criminalintent.activities.CrimeListActivity;
import com.application.education.my.criminalintent.activities.SingleFragmentActivity;
import com.application.education.my.criminalintent.model.Crime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CrimeListActivityTest {

    @Mock
    CrimeListActivity mActivity;
    @Mock
    Crime mCrime;


    @Test
    public void onCrimeSelectedTest (){
        mCrime = mock(Crime.class);
        int position = 5;
        mActivity.onCrimeSelected(mCrime, position);
        verify(mActivity).onCrimeSelected(mCrime,position);
    }
    
    @Test
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void onCrimeUpdatedTest(){
        mActivity.onCrimeUpdated(mCrime);
        verify(mActivity).onCrimeUpdated(mCrime);
    }


}
