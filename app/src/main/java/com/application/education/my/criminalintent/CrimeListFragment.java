package com.application.education.my.criminalintent;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment {
    private final static String SAVE_POSITION_IN_BUNDLE  = "com.application.education.my.criminalintent.CrimeListFragment.save_position";
    private final static String SAVED_SUBTITLE_VISIBLE = "com.application.education.my.criminalintent.CrimeListFragment.subtitle";
    private RecyclerView mRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private Button mCreateFirstCrimeButton;
    private TextView mCreateFirstCrimeTextView;
    private int mClickPosition;
    private int mPosit=0;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null) {
            mPosit = savedInstanceState.getInt(SAVE_POSITION_IN_BUNDLE);
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCreateFirstCrimeButton = (Button) view.findViewById(R.id.create_first_crime_button);
        mCreateFirstCrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crime crime = null;
                try {
                    crime = new Crime(UUID.randomUUID());
                    CrimeLab.getCrimaLab(getActivity()).addCrime(crime);
                    Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getId(),mPosit++);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        mCreateFirstCrimeTextView = (TextView) view.findViewById(R.id.create_first_crime_text_view);

        if (CrimeLab.getCrimaLab(getContext()).getCrimes().size()<=0){

            mCreateFirstCrimeButton.setVisibility(View.VISIBLE);
            mCreateFirstCrimeTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }

        updateUI();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onResume() {
        super.onResume();

        if (CrimeLab.getCrimaLab(getContext()).getCrimes().size()>0 && mCreateFirstCrimeButton!=null) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mCreateFirstCrimeButton.setVisibility(View.GONE);
            mCreateFirstCrimeTextView.setVisibility(View.GONE);
        }

        mClickPosition=getActivity().getIntent().getIntExtra(CrimePagerActivity.EXTRA_CRIME_POSITION,0);
        mCrimeAdapter=null;
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list_menu,menu);

        MenuItem subtitlesItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible){
           subtitlesItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitlesItem.setTitle(R.string.show_subtitle);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_item_new_crime:
                try {
                    Crime crime = new Crime(UUID.randomUUID());
                    CrimeLab.getCrimaLab(getActivity()).addCrime(crime);
                    Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getId(),mPosit++);
                    startActivity(intent);
                    return true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            case R.id.menu_item_show_subtitle:
                mSubtitleVisible=!mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVE_POSITION_IN_BUNDLE,mPosit);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,mSubtitleVisible);
        super.onSaveInstanceState(outState);
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
        updateSubtitle();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void updateSubtitle(){
        CrimeLab crimeLab = CrimeLab.getCrimaLab(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getResources().getQuantityString(R.plurals.subtitle_plural,crimeCount,crimeCount);

        if (!mSubtitleVisible){
            subtitle=null;
        }

        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter (List <Crime> crime){
            mCrimes=crime;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view=layoutInflater.inflate(R.layout.list_item_crime, parent, false);

            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime, position);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;
        private int mPosition;

        public CrimeHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = CrimePagerActivity.newIntent(v.getContext(),mCrime.getId(), mPosition);
                    getActivity().startActivityForResult(intent, 0) ;
                }
            });

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_checkbox);
        }

        public void bindCrime(Crime crime, int position){
            mCrime=crime;
            mPosition=position;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }
    }

}
