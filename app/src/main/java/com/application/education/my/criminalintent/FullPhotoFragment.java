package com.application.education.my.criminalintent;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.application.education.my.criminalintent.utils.PictureUtils;

import java.io.File;

public class FullPhotoFragment extends DialogFragment {
    private ImageView mFullImageView;
    private File mPhotoFile;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPhotoFile=(File) getArguments().getSerializable(CrimeFragment.FULL_PHOTO_BUNDLE_KEY);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.full_photo_dialog_fragment, container, false);

        mFullImageView = (ImageView) v.findViewById(R.id.full_photo_image_view);
        updatePhotoView();

        return v;
    }

    private void updatePhotoView(){
        if(mPhotoFile==null || !mPhotoFile.exists()){
            mFullImageView.setImageDrawable(null);
        }else{
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mFullImageView.setImageBitmap(bitmap);
        }
    }
}
