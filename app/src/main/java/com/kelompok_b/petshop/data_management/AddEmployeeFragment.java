package com.kelompok_b.petshop.data_management;

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
import com.kelompok_b.petshop.R;
import com.kelompok_b.petshop.data_management.database.DatabaseClient;
import com.kelompok_b.petshop.data_management.model.User;

public class AddEmployeeFragment extends Fragment {

    TextInputLayout numberLayout, ageLayout, nameLayout;
    private Button btn_cancel_add, btn_update_add;
//    private TextInputEditText editNumber, editName, editAge;

    String number, age, name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);

        btn_cancel_add = view.findViewById(R.id.btn_cancel);
        btn_update_add = view.findViewById(R.id.btn_update);
//        editNumber = view.findViewById(R.id.input_number);
//        editName = view.findViewById(R.id.input_name);
//        editAge = view.findViewById(R.id.input_age);
        numberLayout = view.findViewById(R.id.input_number_layout);
        ageLayout = view.findViewById(R.id.input_age_layout);
        nameLayout = view.findViewById(R.id.input_name_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_cancel_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back(view);
            }
        });

        btn_update_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = numberLayout.getEditText().getText().toString();
                name = nameLayout.getEditText().getText().toString();
                age = ageLayout.getEditText().getText().toString();


                if (number.isEmpty())
                    numberLayout.setError("Please fill number correctly");

                if (name.isEmpty())
                    nameLayout.setError("Please fill name correctly");

                if (age.isEmpty())
                    ageLayout.setError("Please fill age correctly");

                if (!number.isEmpty() && !name.isEmpty() && !age.isEmpty()) {
                    addUser();
                    back(view);
                }
            }
        });

    }

    private void addUser() {
        class AddUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setName(name);
                user.setAge(Integer.parseInt(age));
                user.setNumber(number);

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .userDao()
                        .insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "User saved", Toast.LENGTH_SHORT).show();
//                editName.setText("");
//                getUsers();
            }
        }

        AddUser add = new AddUser();
        add.execute();
    }

    public void back(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(AddEmployeeFragment.this).commit();
    }
}
