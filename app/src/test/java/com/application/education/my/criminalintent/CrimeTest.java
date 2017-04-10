package com.application.education.my.criminalintent;

import com.application.education.my.criminalintent.model.Crime;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
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

     @Test
     public void getTitleTest(){
      when (mCrime.getTitle()).thenReturn(new String());
         assertThat(mCrime.getTitle(), isA(String.class));
     }

     @Test
     public void setTitleTest (){
         mCrime.setTitle("Test");
         when(mCrime.getTitle()).thenReturn("Test");
         assertSame("Test", mCrime.getTitle());
     }

     @Test
     public void getDateTest(){
         when (mCrime.getDate()).thenReturn(new Date());
         assertThat(mCrime.getDate(), isA(Date.class));
     }

     @Test
     public void setDateTest(){
         mCrime.setDate(new Date());
         Date date = new Date();
         when (mCrime.getDate()).thenReturn(date);
         assertSame(date, mCrime.getDate());
     }

    @Test
    public void isSolvedTest(){
         assertSame(false,mCrime.isSolved());
    }

    @Test
    public void setSolvedTest(){
        mCrime.setSolved(true);
        when (mCrime.isSolved()).thenReturn(true);
        assertSame(true, mCrime.isSolved());
    }

    @Test
    public void getSuspecTest(){
        when (mCrime.getSuspect()).thenReturn(new String());
        assertThat(mCrime.getSuspect(), isA(String.class));
    }

    @Test
    public void setSuspectTest (){
        mCrime.setSuspect("Test");
        when(mCrime.getSuspect()).thenReturn("Test");
        assertSame("Test", mCrime.getSuspect());
    }

    @Test
    public void getPositionTest(){
       when(mCrime.getPosition()).thenReturn(new Integer(1));
        assertThat(mCrime.getPosition(), isA(Integer.class));
    }

    @Test
   public void  setPositionTest(){
       mCrime.setPosition(5);
        when(mCrime.getPosition()).thenReturn(5);
        assertSame(5,mCrime.getPosition());
    }

    @Test
    public void getPhotoFilenameTest(){
        when (mCrime.getId()).thenReturn(new UUID(3,3));
        assertThat("IMG_"+mCrime.getId().toString()+".jpg",isA(String.class));
    }

    @Test
    public void getAddressBookIdTest(){
        when(mCrime.getAddressBookId()).thenReturn(new Integer(1));
        assertThat(mCrime.getAddressBookId(), isA(Integer.class));
    }

    @Test
    public void  setAddressBookIdTest(){
        mCrime.setPosition(5);
        when(mCrime.getAddressBookId()).thenReturn(5);
        assertSame(5,mCrime.getAddressBookId());
    }
}
