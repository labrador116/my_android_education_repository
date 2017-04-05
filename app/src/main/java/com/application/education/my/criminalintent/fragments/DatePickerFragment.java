package com.application.education.my.criminalintent.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.application.education.my.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DatePickerFragment extends DialogFragment {

    private final static String ARG_DATE = "date";
    public final static String EXTRA_DATE ="com.application.education.my.criminalintent.fragments.DatePickerFragment.date";
    private DatePicker mDatePicker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);

        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);

        TextView textView = (TextView) view.findViewById(R.id.dialog_date_text_view_ok);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();

                Date sendDate = new GregorianCalendar(year,month,day).getTime();
                sendResult(Activity.RESULT_OK,sendDate);
                view.setVisibility(View.GONE);
            }
        });



        return view;
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        final Date date = (Date) getArguments().getSerializable(ARG_DATE);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);
//
//        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
//        mDatePicker.init(year,month,day,null);
//
//        return new AlertDialog.Builder(getActivity())
//                .setView(view)
//                .setTitle(R.string.date_picker_title)
//               .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int year = mDatePicker.getYear();
//                        int month = mDatePicker.getMonth();
//                        int day = mDatePicker.getDayOfMonth();
//
//                        Date sendDate = new GregorianCalendar(year,month,day).getTime();
//                        sendResult(Activity.RESULT_OK,sendDate);
//                    }
//                })
//                .create();
//    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }

    private void sendResult(int resultCode, Date date){
        if (getTargetFragment()==null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
