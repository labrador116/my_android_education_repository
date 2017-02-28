package com.application.education.my.criminalintent;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CrimeHolder extends RecyclerView.ViewHolder {

    private TextView mTitleTextView;
    private TextView mDateTextView;
    private CheckBox mSolvedCheckBox;
    private Crime mCrime;
    private int mPosition;
    private CallbackClickPosition mCallbackClickPosition;

    public CrimeHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CrimeActivity.newIntent(v.getContext(),mCrime.getId());
                v.getContext().startActivity(intent);
                mCallbackClickPosition = new CrimeListFragment();

                mCallbackClickPosition.getClickPosition(mPosition);
            }
        });

        mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
        mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
        mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_checkbox);
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        mTitleTextView = titleTextView;
    }

    public TextView getDateTextView() {
        return mDateTextView;
    }

    public void setDateTextView(TextView dateTextView) {
        mDateTextView = dateTextView;
    }

    public CheckBox getSolvedCheckBox() {
        return mSolvedCheckBox;
    }

    public void setSolvedCheckBox(CheckBox solvedCheckBox) {
        mSolvedCheckBox = solvedCheckBox;
    }

    public void bindCrime(Crime crime, int position){
        mCrime=crime;
        mPosition=position;
        mTitleTextView.setText(mCrime.getTitle());
        mDateTextView.setText(mCrime.getDate().toString());
        mSolvedCheckBox.setChecked(mCrime.isSolved());
    }

    public interface CallbackClickPosition{
        void getClickPosition(int position);
    }
}
