package com.kelompok_b.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kelompok_b.petshop.ui.shop.ShopFragment;

public class shopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ShopFragment.newInstance())
                    .commitNow();
        }
    }
}