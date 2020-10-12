package com.kelompok_b.petshop.database;

import com.kelompok_b.petshop.model.Cat;
import java.util.ArrayList;

public class CatList {

    public ArrayList<Cat> catList;
    public CatList(){
        catList = new ArrayList();
        catList.add(Musang);
    }

    public static final Cat Musang = new Cat("John","Akita","1 year","Male","1","1",
            "https://s3.bukalapak.com/uploads/content_attachment/8e84f20130e8d7621b8767c5/w-744/01_Jenis_Ras_Anjing.jpg");

}
