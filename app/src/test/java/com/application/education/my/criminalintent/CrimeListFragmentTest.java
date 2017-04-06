package com.application.education.my.criminalintent;


import android.os.Build;
import android.support.annotation.RequiresApi;

import com.application.education.my.criminalintent.fragments.CrimeListFragment;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CrimeListFragmentTest {

    CrimeListFragment mFragment;
    @Before
    public  void Inint(){
        mFragment=mock(CrimeListFragment.class);
    }

    @Test
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void updateUITest(){
        mFragment.updateUI();
        verify(mFragment).updateUI();
    }

    @Test
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void updateUITestWithPosition(){
        mFragment.updateUI(2);
        verify(mFragment).updateUI(2);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void updateSubtitleTest(){
        mFragment.updateSubtitle();
        verify(mFragment).updateSubtitle();
    }
}
