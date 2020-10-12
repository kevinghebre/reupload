package com.kelompok_b.petshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok_b.petshop.adapter.DogRecyclerViewAdapter;
import com.kelompok_b.petshop.database.DogList;
import com.kelompok_b.petshop.model.Dog;

import java.util.ArrayList;

public class DogShopFragment extends Fragment {

    private ArrayList<Dog> listDog;
    private RecyclerView recyclerView;
    private DogRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static DogShopFragment newInstance() {
        return new DogShopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dog_shop_fragment, container, false);

        //get data mahasiswa
        listDog = new DogList().dogList;

        //recycler view
        recyclerView = root.findViewById(R.id.recycler_pet_list);
        adapter = new DogRecyclerViewAdapter(getContext(), listDog);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return root;
    }

}
