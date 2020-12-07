package com.kelompok_b.petshop.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kelompok_b.petshop.Api.PetAPI;
import com.kelompok_b.petshop.R;
import com.kelompok_b.petshop.Views.TambahEditCat;
import com.kelompok_b.petshop.model.Dog;
import com.kelompok_b.petshop.model.Pet;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.DELETE;

public class AdapterDog extends RecyclerView.Adapter<AdapterDog.adapterDogViewHolder> {

    private List<Dog> dogList;
    private List<Dog> dogListFiltered;
    private Context context;
    private View view;
    private AdapterDog.deleteItemListener mListener;

    public AdapterDog(Context context, List<Dog> dogList,
                      AdapterDog.deleteItemListener mListener) {
        this.context            = context;
        this.dogList           = dogList;
        this.dogListFiltered   = dogList;
        this.mListener          = mListener;
    }

    public interface deleteItemListener {
        void deleteItem( Boolean delete);
    }

    @NonNull
    @Override
    public AdapterDog.adapterDogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.activity_adapter_dog, parent, false);
        return new AdapterDog.adapterDogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterDogViewHolder holder, int position) {
        final Dog dog = dogListFiltered.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        holder.pet_name.setText(dog.getNama_dog());
        holder.type_name.setText(dog.getJenis_dog());
        holder.weight.setText(formatter.format(dog.getBerat_dog()) + "Kg");
        holder.age.setText(formatter.format(dog.getUmur_dog()) + "Tahun");
        holder.gender.setText(dog.getJk_dog());
        holder.price.setText("Rp "+ formatter.format(dog.getHarga_dog()));
        Glide.with(context)
                .load(PetAPI.URL_IMAGE+dog.getImage_dog())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivGambar);

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle data = new Bundle();
                data.putSerializable("dog", dog);
                data.putString("status", "edit");
                TambahEditCat tambahEditDog = new TambahEditCat();
                tambahEditDog.setArguments(data);
                loadFragment(tambahEditDog);
            }
        });

        holder.ivHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Anda yakin ingin menghapus Dog ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteDog(String.valueOf(dog.getIdDog()));
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dogListFiltered != null) ? dogListFiltered.size() : 0;
    }

    public class adapterDogViewHolder extends RecyclerView.ViewHolder {
        private TextView type_name, pet_name, gender, price, weight, age, ivEdit, ivHapus;;
        private ImageView ivGambar;
        private CardView cardDog;

        public adapterDogViewHolder(@NonNull View itemView) {
            super(itemView);
            pet_name        = itemView.findViewById(R.id.tvName);
            type_name       = itemView.findViewById(R.id.tvType);
            gender          = itemView.findViewById(R.id.tvGender);
            age             = itemView.findViewById(R.id.tvAge);
            weight          = itemView.findViewById(R.id.tvWeight);
            price           = itemView.findViewById(R.id.tvPrice);
            ivGambar        = itemView.findViewById(R.id.ivFotoCat);
            ivEdit          = (TextView) itemView.findViewById(R.id.ivEdit);
            ivHapus         = (TextView) itemView.findViewById(R.id.ivHapus);
            cardDog         = itemView.findViewById(R.id.cardDog);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String userInput = charSequence.toString();
                if (userInput.isEmpty()) {
                    dogListFiltered = dogList;
                }
                else {
                    List<Dog> filteredList = new ArrayList<>();
                    for(Dog dog : dogList) {
                        if(String.valueOf(dog.getNama_dog()).toLowerCase().contains(userInput) ||
                                dog.getJenis_dog().toLowerCase().contains(userInput)) {
                            filteredList.add(dog);
                        }
                    }
                    dogListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dogListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dogListFiltered = (ArrayList<Dog>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void loadFragment(Fragment fragment) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_view_dog,fragment)
                .commit();
    }

    public void deleteDog(String idDog){
        //Tambahkan hapus buku disini
        RequestQueue queue = Volley.newRequestQueue(context);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menghapus data Anjing");
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        //Memulai membuat permintaan request menghapus data ke jaringan
        StringRequest stringRequest = new StringRequest(DELETE, PetAPI.URL_DELETE + idDog, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error
                progressDialog.dismiss();
                try {
                    //Mengubah response string menjadi object
                    JSONObject obj = new JSONObject(response);
                    //obj.getString("message") digunakan untuk mengambil pesan message dari response
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    mListener.deleteItem(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }
}
