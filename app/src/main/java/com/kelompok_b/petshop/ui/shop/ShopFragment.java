package com.kelompok_b.petshop.ui.shop;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok_b.petshop.Pet;
import com.kelompok_b.petshop.PetList;
import com.kelompok_b.petshop.PetRecyclerViewAdapter;
import com.kelompok_b.petshop.R;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    private ShopViewModel mViewModel;
    private ArrayList<Pet> ListPet;
    private RecyclerView recyclerView;
    private PetRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_fragment, container, false);

        //get data mahasiswa
        ListPet = new PetList().PetList;

        //recycler view
        recyclerView = root.findViewById(R.id.recycler_pet_list);
        adapter = new PetRecyclerViewAdapter(getContext(), ListPet);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return root ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ShopViewModel.class);
        // TODO: Use the ViewModel
    }
}