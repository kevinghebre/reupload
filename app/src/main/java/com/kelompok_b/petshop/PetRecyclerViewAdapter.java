package com.kelompok_b.petshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok_b.petshop.databinding.PetAdapterRecyclerViewBinding;

import java.util.List;

public class PetRecyclerViewAdapter extends RecyclerView.Adapter<PetRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Pet> result;

    public PetRecyclerViewAdapter(Context context, List<Pet> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PetAdapterRecyclerViewBinding petAdapterRecyclerViewBinding = PetAdapterRecyclerViewBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(petAdapterRecyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Pet pet = result.get(position);
        holder.adapterRecyclerViewBinding.setPet(pet);
        holder.adapterRecyclerViewBinding.executePendingBindings();
        //            holder.adapterRecyclerViewBinding.
//            holder.adapterRecyclerViewBinding.executePendingBindings();

//        if (!mhs.getImgURL().equals("")){
//            Glide.with(context)
//                    .load(mhs.getImgURL())
//                    .into(holder.foto_profil);
//        }else{
//            holder.foto_profil.setImageResource(R.drawable.ic_broken_image);
//        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        PetAdapterRecyclerViewBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull final PetAdapterRecyclerViewBinding adapterRecyclerViewBinding) {
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


//    private Context context;
//    private List<Pet> result;
//
//    PetAdapterRecyclerViewBinding binding;
//
//    public PetRecyclerViewAdapter(Context context, List<Pet> result) {
//        this.context = context;
//        this.result = result;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pet_adapter_recycler_view, parent, false);
//        return new MyViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
//        final Pet pet = result.get(position);
//        binding.setPet(pet);
//        binding.setImageUrl(pet.imageUrl);
//    }
//
//    @Override
//    public int getItemCount() {
//        return result.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private PetAdapterRecyclerViewBinding binding;
//
//        public MyViewHolder(@NonNull PetAdapterRecyclerViewBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        public void onClick(View view) {
//            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
//        }
//    }
//}