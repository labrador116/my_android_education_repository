package com.application.education.my.criminalintent;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.application.education.my.criminalintent.database.CrimeCursorWrapper;
import com.application.education.my.criminalintent.model.Crime;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import java.text.ParseException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrimeCursorWrapperTest {

    @Test
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void  getCrimeTest() throws ParseException {
        CrimeCursorWrapper cursorWrapper = Mockito.mock(CrimeCursorWrapper.class);
        when (cursorWrapper.getCrime()).thenReturn(mock(Crime.class));

        try {
            Assert.assertThat(cursorWrapper.getCrime(), isA(Crime.class));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
