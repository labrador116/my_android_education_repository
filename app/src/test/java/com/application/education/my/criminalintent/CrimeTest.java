package com.application.education.my.criminalintent;

import com.application.education.my.criminalintent.model.Crime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrimeTest {
    Crime mCrime;

    @Before
    public  void initCrime(){
        mCrime= mock(Crime.class);
    }

    @Test
    public void  getIdTest(){
        when (mCrime.getId()).thenReturn(new UUID(3,3));
        assertThat(mCrime.getId(), isA(UUID.class));
    }

    @Test
    public void  setIdTest(){
        UUID uuid = new UUID(3,3);
        when (mCrime.getId()).thenReturn(uuid);
        mCrime.setId(uuid);
        assertSame(uuid, mCrime.getId());
    }


}
