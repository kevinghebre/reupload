package com.kelompok_b.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kelompok_b.petshop.ui.profil.ProfilFragment;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ProfilFragment.newInstance())
                    .commitNow();
        }
    }
}