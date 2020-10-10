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

import com.kelompok_b.petshop.R;

import static android.app.Activity.RESULT_OK;

public class ProfilFragment extends Fragment {

    private ProfilViewModel mViewModel;
    ImageView edit_pic, imageView;
    int IMAGE_REQUEST_CODE = 1000;
    private static final String TAG = "MainActivity";

    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profil_fragment, container, false);

        imageView = root.findViewById(R.id.profilImage);
        edit_pic = root.findViewById(R.id.edit_pic);
        edit_pic.setOnClickListener(new View.OnClickListener() {
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
        mViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
        // TODO: Use the ViewModel
    }

    public void takePhoto(View view) {

        //Creating a new intent to take photos from a Camera App
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, 100);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Log.e(TAG, "onActivityResult: Photo taken now handle the photo");

                    //Getting the thumbnail image from the key data
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                    //Setting the Bitmap inside our image view
                    imageView.setImageBitmap(bitmap);
                    break;
            }
        }
    }
}

