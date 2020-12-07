package com.kelompok_b.petshop.Views;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.kelompok_b.petshop.Api.PetAPI;
import com.kelompok_b.petshop.R;
import com.kelompok_b.petshop.model.Cat;
import com.kelompok_b.petshop.model.Pet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

public class TambahEditCat extends Fragment {
    private TextInputEditText txtNamaPet, txtHarga, txtUmur, txtBerat, txtJenisKucing;
    private String status, selectedTipe, selectedJenisKelamin;
    private ImageView ivGambar;
    private Button btnSimpan, btnBatal, btnUnggah;
    private String selected;
    private int idPet;
    private Cat cat;
    private View view;
    private Bitmap bitmap;
    private Uri selectedImage = null;
    private static final int PERMISSION_CODE = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tambah_edit_cat, container, false);
        init();
        setAttribut();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(menu.findItem(R.id.btnSearch) != null)
            menu.findItem(R.id.btnSearch).setVisible(false);
        if(menu.findItem(R.id.btnAdd) != null)
            menu.findItem(R.id.btnAdd).setVisible(false);
    }

    public void init(){
        cat                     = (Cat) getArguments().getSerializable("cat");
        txtNamaPet              = view.findViewById(R.id.txtNamaPet);
        txtUmur                 = view.findViewById(R.id.txtUmur);
        txtBerat                = view.findViewById(R.id.txtBerat);
        txtHarga                = view.findViewById(R.id.txtHarga);
        txtJenisKucing           = view.findViewById(R.id.txtJenisHewan);
        btnSimpan               = view.findViewById(R.id.btnSimpan);
        btnBatal                = view.findViewById(R.id.btnBatal);
        btnUnggah               = view.findViewById(R.id.btnUnggah);
        ivGambar                = view.findViewById(R.id.ivGambar);
        final String[] JKArray = getResources().getStringArray(R.array.jenisKelamin);

        status = getArguments().getString("status");
        if(status.equals("edit"))
        {
            idPet = cat.getIdCat();
            txtNamaPet.setText(cat.getNama_cat());
            txtJenisKucing.setText(cat.getJenis_cat());
            txtBerat.setText(String.valueOf(cat.getBerat_cat()));
            txtUmur.setText(String.valueOf(cat.getUmur_cat()));
            txtHarga.setText(String.valueOf(Math.round(cat.getHarga_cat())));
            Glide.with(view.getContext())
                    .load(PetAPI.URL_IMAGE +cat.getImage_cat())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivGambar);

            for(String jk : JKArray)
            {
                if(jk.equals(cat.getJk_cat()))
                    selectedJenisKelamin = jk;
            }

        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.jenisKelamin, android.R.layout.simple_spinner_item);
        final AutoCompleteTextView jenisKelaminDropdown = view.findViewById(R.id.txtJenis_Kelamin);
        jenisKelaminDropdown.setText(selectedJenisKelamin);
        jenisKelaminDropdown.setAdapter(adapter);
        jenisKelaminDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedJenisKelamin = jenisKelaminDropdown.getEditableText().toString();
            }
        });
    }

    private void setAttribut() {
        btnUnggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
                View view = layoutInflater.inflate(R.layout.pilih_media, null);

                final AlertDialog alertD = new AlertDialog.Builder(view.getContext()).create();

                Button btnKamera = (Button) view.findViewById(R.id.btnKamera);
                Button btnGaleri = (Button) view.findViewById(R.id.btnGaleri);

                btnKamera.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selected="kamera";
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                        {
                            if(getActivity().checkSelfPermission(Manifest.permission.CAMERA)==
                                    PackageManager.PERMISSION_DENIED ||
                                    getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                            PackageManager.PERMISSION_DENIED){
                                String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permission,PERMISSION_CODE);
                            }
                            else{
                                openCamera();
                            }
                        }
                        else{
                            openCamera();
                        }
                        alertD.dismiss();
                    }
                });

                btnGaleri.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selected="galeri";
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                        {
                            if(getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                    PackageManager.PERMISSION_DENIED){
                                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permission,PERMISSION_CODE);
                            }
                            else{
                                openGallery();
                            }
                        }
                        else{
                            openGallery();
                        }
                        alertD.dismiss();
                    }
                });

                alertD.setView(view);
                alertD.show();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_cat  = txtNamaPet.getText().toString();
                String jenis_cat = txtJenisKucing.getText().toString();
                String jk_cat = selectedJenisKelamin;



                if(nama_cat.isEmpty() || txtUmur.getText().toString().isEmpty() || txtHarga.getText().toString().isEmpty() || jenis_cat.isEmpty() || jk_cat.isEmpty()
                        || txtBerat.getText().toString().isEmpty() )
                    Toast.makeText(getContext(), "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                else{
                    Double harga_cat    = Double.parseDouble(txtHarga.getText().toString());
                    Double berat_cat   = Double.parseDouble(txtBerat.getText().toString());
                    Double umur_cat      = Double.parseDouble(txtUmur.getText().toString());

                    cat = new Cat(nama_cat,jenis_cat, jk_cat, harga_cat,berat_cat,umur_cat);
                    if(status.equals("tambah")) {
                        String bytesString = "";
                        if (bitmap != null) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            byte[] bytes = byteArrayOutputStream.toByteArray();
                            bytesString = Base64.encodeToString(bytes, Base64.DEFAULT);
                        }
                        tambahCat(cat, bytesString);
                    }
                    else {
                        String bytesString = "";
                        if (bitmap != null) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            byte[] bytes = byteArrayOutputStream.toByteArray();
                            bytesString = Base64.encodeToString(bytes, Base64.DEFAULT);
                        }
                        editCat(cat, bytesString);
                    }
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ViewsCat());
            }
        });
    }

    private void openGallery(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    if(selected.equals("kamera"))
                        openCamera();
                    else
                        openGallery();
                }else{
                    Toast.makeText(getContext() ,"Permision denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1)
        {
            selectedImage = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            ivGambar.setImageBitmap(bitmap);
            bitmap = getResizedBitmap(bitmap, 512);
        }
        else if(resultCode == RESULT_OK && requestCode == 2)
        {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            ivGambar.setImageBitmap(bitmap);
            bitmap = getResizedBitmap(bitmap, 512);
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            fragmentTransaction.setReorderingAllowed(false);
        }
        fragmentTransaction.replace(R.id.frame_tambah_edit_cat, fragment)
                .detach(this)
                .attach(this)
                .commit();
    }

    public void closeFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(TambahEditCat.this).detach(this)
                .attach(this).commit();
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void tambahCat(final Cat cat, final String gambar){
        //Tambahkan tambah buku disini
        //Pendeklarasian queue
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menambahkan data Kucing");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        //Memulai membuat permintaan request menghapus data ke jaringan
        StringRequest stringRequest = new StringRequest(POST, PetAPI.URL_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error
                progressDialog.dismiss();
                try {
                    //Mengubah response string menjadi object
                    JSONObject obj = new JSONObject(response);
                    //obj.getString("message") digunakan untuk mengambil pesan status dari response
                    if(obj.getString("status").equals("Success"))
                    {
                        loadFragment(new ViewsCat());
                    }

                    //obj.getString("message") digunakan untuk mengambil pesan message dari response
                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                /*
                    Disini adalah proses memasukan/mengirimkan parameter key dengan data value,
                    dan nama key nya harus sesuai dengan parameter key yang diminta oleh jaringan
                    API.
                */
                Map<String, String>  params = new HashMap<String, String>();
                params.put("namaPet", cat.getNama_cat());
                params.put("jenisHewan", cat.getJenis_cat());
                params.put("umur", cat.getUmur_cat().toString());
                params.put("harga", cat.getHarga_cat().toString());
                params.put("berat", cat.getBerat_cat().toString());
                params.put("jenisKelamin", cat.getJk_cat());
                params.put("gambar", gambar);

                return params;
            }
        };

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }

    public void editCat(final Cat cat, final String gambar) {
        //Tambahkan edit buku disini
        //Tambahkan tambah buku disini
        //Pendeklarasian queue
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Mengedit data Kucing");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        //Memulai membuat permintaan request menghapus data ke jaringan
        StringRequest stringRequest = new StringRequest(PUT, PetAPI.URL_UPDATE + idPet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error
                progressDialog.dismiss();
                try {
                    //Mengubah response string menjadi object
                    JSONObject obj = new JSONObject(response);
                    //obj.getString("message") digunakan untuk mengambil pesan status dari response
                    if(obj.getString("status").equals("Success"))
                    {
                        loadFragment(new ViewsCat());
                    }

                    //obj.getString("message") digunakan untuk mengambil pesan message dari response
                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                /*
                    Disini adalah proses memasukan/mengirimkan parameter key dengan data value,
                    dan nama key nya harus sesuai dengan parameter key yang diminta oleh jaringan
                    API.
                */
                Map<String, String>  params = new HashMap<String, String>();
                params.put("namaPet", cat.getNama_cat());
                params.put("jenisHewan", cat.getJenis_cat());
                params.put("umur", cat.getUmur_cat().toString());
                params.put("harga", cat.getHarga_cat().toString());
                params.put("berat", cat.getBerat_cat().toString());
                params.put("jenisKelamin", cat.getJk_cat());
                params.put("gambar", gambar);
                return params;
            }
        };

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }
}