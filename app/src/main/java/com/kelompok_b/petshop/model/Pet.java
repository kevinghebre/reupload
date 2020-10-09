package com.kelompok_b.petshop.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Pet  {

    public String name;
    public String race;
    public String age;
    public String gender;
    public String size;
    public String height;
    public String imgURL;

    public Pet(String name, String race, String age, String gender, String size, String height, String imgURL) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.height = height;
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage (ImageView imgView, String imgURL){
        Glide.with(imgView)
                .load(imgURL)
                .into(imgView);

    }
}
