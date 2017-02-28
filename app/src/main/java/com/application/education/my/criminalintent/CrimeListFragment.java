package com.application.education.my.criminalintent;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CrimeListFragment extends Fragment implements CrimeHolder.CallbackClickPosition {
    private RecyclerView mRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private static int mClickPosition;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.getCrimaLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mCrimeAdapter==null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mCrimeAdapter);
        }else{
            mCrimeAdapter.notifyItemChanged(mClickPosition);
        }
    }

    @Override
    public void getClickPosition(int position) {
        mClickPosition=position;
    }
}
