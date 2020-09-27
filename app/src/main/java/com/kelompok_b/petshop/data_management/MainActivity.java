package com.kelompok_b.petshop.data_management;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kevinghebre.datapersistent_unguided_9774.adapter.UserRecyclerViewAdapter;
import com.kevinghebre.datapersistent_unguided_9774.database.DatabaseClient;
import com.kevinghebre.datapersistent_unguided_9774.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private TextInputEditText editText;
//    private Button addBtn;

    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout refreshLayout;
    private FloatingActionButton addEmployee;
//    private SearchView input_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        editText = findViewById(R.id.input_name);

        refreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final EditText searchView = findViewById(R.id.input_search);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterUser(searchView.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        refresh();
        addData();
        getUsers();
    }

    private void addData() {
        addEmployee = findViewById(R.id.addNewEmployee);
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.frame_layout, new AddEmployeeFragment());
                fragmentTransaction.commit();
            }
        });
    }

    private void refresh() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void filterUser(final String search) {
        class FilterUser extends AsyncTask<Void, Void, List<User>> {
            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> filteredList = new ArrayList<>();
                List<User> userList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getAll();
                if (search == null || search.length() == 0) {
                    filteredList.addAll(userList);
                } else {
                    String filterPattern = search.toLowerCase().trim();
                    for (User item : userList) {
                        if (item.getName().toLowerCase().contains(filterPattern))
                            filteredList.add(item);
                    }
                }
                return filteredList;
            }

            @Override
            protected void onPostExecute(List<User> userList) {
                super.onPostExecute(userList);
                UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(MainActivity.this, userList);
                recyclerView.setAdapter(adapter);
            }
        }

        FilterUser filter = new FilterUser();
        filter.execute();
    }

    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(MainActivity.this, users);
                recyclerView.setAdapter(adapter);
                if (users.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }
        GetUsers get = new GetUsers();
        get.execute();
    }
}