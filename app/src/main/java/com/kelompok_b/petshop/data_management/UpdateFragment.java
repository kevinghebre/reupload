package com.kelompok_b.petshop.data_management;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;
import com.kevinghebre.datapersistent_unguided_9774.database.DatabaseClient;
import com.kevinghebre.datapersistent_unguided_9774.model.User;

public class UpdateFragment extends Fragment {

    TextInputLayout nameLayout, numberLayout, ageLayout;
    //    TextInputEditText editName, editAge, editNumber;
    Button saveBtn, deleteBtn, cancelBtn;
    User user;

    String number, age, name;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        user = (User) getArguments().getSerializable("user");

        numberLayout = view.findViewById(R.id.input_number_layout);
        nameLayout= view.findViewById(R.id.input_name_layout);
        ageLayout= view.findViewById(R.id.input_age_layout);

        try {
            numberLayout.getEditText().setText(user.getNumber());
            nameLayout.getEditText().setText(user.getName());
            ageLayout.getEditText().setText(user.getStringAge());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveBtn = view.findViewById(R.id.btn_update);
        deleteBtn = view.findViewById(R.id.btn_delete);
        cancelBtn = view.findViewById(R.id.btn_cancel);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = numberLayout.getEditText().getText().toString();
                name = nameLayout.getEditText().getText().toString();
                age = ageLayout.getEditText().getText().toString();

                if(number.isEmpty())
                    numberLayout.setError("Please fill number correctly");

                if(name.isEmpty())
                    nameLayout.setError("Please fill name correctly");

                if(age.isEmpty())
                    ageLayout.setError("Please fill age correctly");

                if(!number.isEmpty() && !name.isEmpty() && !age.isEmpty()) {
                    user.setName(name);
                    user.setNumber(number);
                    user.setAge(Integer.parseInt(age));

                    update(user);
                    backFunction();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete")
                        .setMessage("Ingin Menghapus?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                delete(user);
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
                backFunction();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFunction();
            }
        });
    }

    private void update(final User user) {
        class UpdateUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .userDao()
                        .update(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "User updated", Toast.LENGTH_SHORT).show();
                backFunction();
            }
        }

        UpdateUser update = new UpdateUser();
        update.execute();
    }

    private void delete(final User user) {
        class DeleteUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .userDao()
                        .delete(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "User deleted", Toast.LENGTH_SHORT).show();
                backFunction();
            }
        }

        DeleteUser delete = new DeleteUser();
        delete.execute();
    }

    public void backFunction() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(UpdateFragment.this).commit();
    }

}