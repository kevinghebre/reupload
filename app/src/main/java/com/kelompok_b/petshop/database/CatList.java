package com.kelompok_b.petshop.database;

import com.kelompok_b.petshop.model.Cat;
import java.util.ArrayList;

public class CatList {

    public ArrayList<Cat> catList;
    public CatList(){
        catList = new ArrayList();
        catList.add(Kampung);
        catList.add(Persia);
        catList.add(British);
        catList.add(Bengal);
        catList.add(Spynx);
        catList.add(Munchkin);
        catList.add(Siberian);
        catList.add(Himalayan);
        catList.add(Siamese);
        catList.add(Ragdoll);

    }

    public static final Cat Kampung = new Cat("Muke","Kampung","1 year","Male","1","1",
            "https://www.99.co/blog/indonesia/wp-content/uploads/2019/02/cara-mengusir-kucing-cover.jpg");
    public static final Cat Persia = new Cat("Elsa","Persia","2 year","Female","1","1",
            "https://cdnaz.cekaja.com/media/2020/03/822_Artikel-CA20_-tips-memelihara-kucing-persia.jpg");
    public static final Cat British = new Cat("John","British Short Hair","1 year","Male","2","1",
            "https://kucingpedia.com/wp-content/uploads/2019/08/Kucing-British-Shorthair-630x380.jpg");
    public static final Cat Bengal = new Cat("Tarzan","Bengal","1 year","Male","2","2",
            "https://perfectpets.com.au/uploads/files/getfile/type/breed/id/314/file/789429065f029c1c30f9b5.29682828-breed-314.png");
    public static final Cat Spynx = new Cat("Firaun","Spynx","1 year","Male","2","1",
            "https://kucingpedia.com/wp-content/uploads/2018/04/Gambar-Anak-Kucing-Sphynx-630x380.png");
    public static final Cat Munchkin = new Cat("Maria","Munchkin","1 year","Female","2","1",
            "https://cdn.idntimes.com/content-images/community/2019/02/41246892-465097870652461-7196242739812368384-n-19eb604e70408547fed1e90bddeb793c_600x400.jpg");
    public static final Cat Siberian = new Cat("Jack","Siberian","1 year","Male","2","1",
            "https://cdn.idntimes.com/content-images/community/2020/01/siberian1-32a60a7046f28b1adb4d3844c20b23bb_600x400.jpg");
    public static final Cat Himalayan = new Cat("Kitty","Himalayan","1 year","Female","2","1",
            "https://upload.wikimedia.org/wikipedia/id/6/6f/KucingHimalaya.jpg");
    public static final Cat Siamese = new Cat("Laura","Siamese","1 year","Female","2","1",
            "https://www.kanal.web.id/wp-content/uploads/2016/01/kucing-siam.jpg");
    public static final Cat Ragdoll = new Cat("Pie","Ragdoll","1 year","Female","2","1",
            "https://ecs7.tokopedia.net/img/cache/700/product-1/2020/8/4/9290551/9290551_74ec93f0-e887-4eab-bfb0-85c58b4a2abd_1080_1080.jpg");


}
