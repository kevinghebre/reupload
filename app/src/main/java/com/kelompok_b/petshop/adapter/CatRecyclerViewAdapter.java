package com.kelompok_b.petshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok_b.petshop.databinding.CatAdapterRecyclerViewBinding;
import com.kelompok_b.petshop.databinding.PetAdapterRecyclerViewBinding;
import com.kelompok_b.petshop.model.Cat;

import java.util.List;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Cat> result;

    public CatRecyclerViewAdapter(Context context, List<Cat> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CatAdapterRecyclerViewBinding catAdapterRecyclerViewBinding = CatAdapterRecyclerViewBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(catAdapterRecyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Cat cat = result.get(position);
        holder.adapterRecyclerViewBinding.setCat(cat);
        holder.adapterRecyclerViewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CatAdapterRecyclerViewBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull final CatAdapterRecyclerViewBinding adapterRecyclerViewBinding) {
            super(adapterRecyclerViewBinding.getRoot());
            this.adapterRecyclerViewBinding = adapterRecyclerViewBinding;

            adapterRecyclerViewBinding.ivFotoProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Ciee liat profil " + result.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

