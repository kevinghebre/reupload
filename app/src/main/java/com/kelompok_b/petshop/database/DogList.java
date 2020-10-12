package com.kelompok_b.petshop.database;

import com.kelompok_b.petshop.model.Dog;

import java.util.ArrayList;

public class DogList {
    public ArrayList<Dog> dogList;

    public DogList(){
        dogList = new ArrayList();
        dogList.add(Akita);
        dogList.add(Beagle);
        dogList.add(Chihuahua);
        dogList.add(Maltese);
        dogList.add(Pomeranian);
        dogList.add(Shiba_Inu);
        dogList.add(Shih_Tzu);
        dogList.add(Pekingese);
        dogList.add(Golden_Retriever);
        dogList.add(Alaskan_Malamute);

    }
    public static final Dog Akita = new Dog("John","Akita","1 year","Male","1","1",
            "https://s3.bukalapak.com/uploads/content_attachment/8e84f20130e8d7621b8767c5/w-744/01_Jenis_Ras_Anjing.jpg");
    public static final Dog Beagle = new Dog("Beagle","Beagle","1 year","Male","1","1",
            "https://s4.bukalapak.com/uploads/content_attachment/46f38cf330e8d762bb8767c5/w-744/02_Jenis_Ras_Anjing.jpg");
    public static final Dog Chihuahua = new Dog("Chihuahua","Chihuahua","1 year","Male","1","1",
            "https://s2.bukalapak.com/uploads/content_attachment/26aee58650e8d7621d8767c5/w-744/03_Jenis_Ras_Anjing.jpg");
    public static final Dog Maltese = new Dog("Maltese","Maltese","1 year","Male","1","1",
            "https://s3.bukalapak.com/uploads/content_attachment/d3387a1a20e8d7625b8767c5/w-744/04__Jenis_Ras_Anjing.jpg");
    public static final Dog Pomeranian = new Dog("Pomeranian","Pomeranian","1 year","Male","1","1",
            "https://s3.bukalapak.com/uploads/content_attachment/37fb66fb20e8d762ad8767c5/w-744/05_Jenis_Ras_Anjing.jpg");
    public static final Dog Shiba_Inu = new Dog("Shiba_Inu","Shiba_Inu","1 year","Male","1","1",
            "https://s2.bukalapak.com/uploads/content_attachment/288a706a20e8d7623c8767c5/w-744/06__Jenis_Ras_Anjing.jpg");
    public static final Dog Shih_Tzu = new Dog("Shih_Tzu","Shih_Tzu","1 year","Male","1","1",
            "https://s2.bukalapak.com/uploads/content_attachment/c949e49720e8d7625e8767c5/w-744/07__Jenis_Ras_Anjing.jpg");
    public static final Dog Pekingese = new Dog("Pekingese","Pekingese","1 year","Male","1","1",
            "https://s4.bukalapak.com/uploads/content_attachment/42333aa430e8d762c09767c5/w-744/08_Jenis_Ras_Anjing.jpg");
    public static final Dog Golden_Retriever = new Dog("Golden_Retriever","Golden_Retriever","1 year","Male","1","1",
            "https://s1.bukalapak.com/uploads/content_attachment/1eda7b6e20e8d762319767c5/w-744/09__Jenis_Ras_Anjing.jpg");
    public static final Dog Alaskan_Malamute = new Dog("Alaskan_Malamute","Alaskan_Malamute","1 year","Male","1","1",
            "https://s3.bukalapak.com/uploads/content_attachment/8e84f20130e8d7621b8767c5/w-744/01_Jenis_Ras_Anjing.jpg");

}
