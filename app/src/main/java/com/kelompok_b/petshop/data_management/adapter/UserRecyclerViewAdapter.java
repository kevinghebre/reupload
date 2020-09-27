package com.kelompok_b.petshop.data_management.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.kelompok_b.petshop.R;
import com.kelompok_b.petshop.data_management.UpdateFragment;
import com.kelompok_b.petshop.data_management.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> implements Filterable {

    private User user;
    private Context context;
    private List<User> userList;
    private List<User> userListFull = new ArrayList<>();

    public UserRecyclerViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        userListFull.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        user = userList.get(position);
        holder.item_list_nama.setText(user.getName());
        holder.item_list_age.setText(user.getStringAge());
        holder.item_list_number.setText(user.getNumber());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView item_list_nama, item_list_number, item_list_age;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            item_list_age = itemView.findViewById(R.id.textview_age);
            item_list_nama = itemView.findViewById(R.id.textview_name);
            item_list_number = itemView.findViewById(R.id.textview_number);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            User user = userList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("user", user);
            UpdateFragment updateFragment = new UpdateFragment();
            updateFragment.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, updateFragment)
                    .commit();
        }
    }

    public Filter getFilter() {
        return userFilter;
    }

    private Filter userFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<User> filterList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                filterList.addAll(userListFull) ;
            }
            else {
                String pattern = charSequence.toString().toLowerCase().trim();

                for(User item : userListFull) {
                    if(item.getName().toLowerCase().contains(pattern))
                        filterList.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            userList.clear();

            userList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}



