package com.kelompok_b.petshop.ui.profil;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.imageview.ShapeableImageView;
import com.kelompok_b.petshop.R;

public class ProfilFragment extends Fragment {

    private MainViewModel mViewModel;
    private static final String TAG = "MainActivity";

    ShapeableImageView edit_picture;
    int IMAGE_REQUEST_CODE = 1000;      //should be unique to handle image capture


    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profil, container, false);

        edit_picture = root.findViewById(R.id.edit_pic);

        edit_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               takePhoto(view);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public void takePhoto(View view) {

        //Creating a new intent to take photos from a Camera App
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /*Checking if the any Camera app is present to handle taking pictures
        i.e. Take a photo with a camera app*/
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == IMAGE_REQUEST_CODE) {
//            switch (resultCode) {
//                case RESULT_OK:
//                    Log.e(TAG, "onActivityResult: Photo taken now handle the photo");
//
//                    //Getting the thumbnail image from the key data
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//                    //Setting the Bitmap inside our image view
//                    imageView.setImageBitmap(bitmap);
//                    break;
//
//                case RESULT_CANCELED:
//                    Log.e(TAG, "onActivityResult: User pressed the Cancel button");
//                    break;
//
//                default:
//                    Log.e(TAG, "onActivityResult: This is default");
//            }
//
//        }
//    }

}