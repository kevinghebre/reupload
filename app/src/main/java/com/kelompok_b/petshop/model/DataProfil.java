package com.kelompok_b.petshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DataProfil {

    private String fullname;
    private String email;
    private String password;
    private String phone_number;
    private String gender;
    private int age;

    public DataProfil(String fullname, String email, String password, String phone_number, String gender, int age) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.gender = gender;
        this.age = age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
