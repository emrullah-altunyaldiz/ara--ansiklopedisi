package com.aracansiklopedisi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kategori {
    private final String ad;
    private final Kategori ustKategori;
    private final List<Kategori> altKategoriler = new ArrayList<>();

    public Kategori(String ad,Kategori ustKategori) {
        if(ad == null || ad.isBlank()){
            throw new IllegalArgumentException("Kategori adi bos olamaz!");
        }
        this.ad = ad.trim();
        this.ustKategori = ustKategori;
        if(ustKategori != null){
            ustKategori.altKategoriler.add(this);
        }
    }
    public String getAd(){
        return this.ad;
    }
    public Kategori getUstKategori(){
        return this.ustKategori;
    }
    public List<Kategori> getAltKategoriler(){
        return Collections.unmodifiableList(altKategoriler);
    }
    public boolean kokMu(){
        return ustKategori == null;
    }
    public void agaciYazdir(){
        agaciYazdir(0);
    }
    private void agaciYazdir(int seviye){
        System.out.println(" ".repeat(seviye) + "- " + this.ad);
        for(Kategori alt : altKategoriler){
            alt.agaciYazdir(seviye + 1);
        }
    }
}
