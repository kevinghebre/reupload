package com.kelompok_b.petshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok_b.petshop.adapter.CatRecyclerViewAdapter;
import com.kelompok_b.petshop.adapter.DogRecyclerViewAdapter;
import com.kelompok_b.petshop.database.CatList;
import com.kelompok_b.petshop.model.Cat;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CatShopFragment extends Fragment {

    private ArrayList<Cat> listCat;
    private RecyclerView recyclerView;
    private CatRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static CatShopFragment newInstance(){
        return new CatShopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cat_shop_fragment, container, false);

        //get data mahasiswa
        listCat = new CatList().catList;

        //recycler view
//        recyclerView = root.findViewById(R.id)


        recyclerView = root.findViewById(R.id.recycler_cat_list);
        adapter = new CatRecyclerViewAdapter(getContext(), listCat);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return root;
    }

}
