package com.aracansiklopedisi.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Model {
    private final String ad;
    private final Marka marka;
    private final Kategori anaKategori;
    private final Set<Kategori> kategoriler = new LinkedHashSet<>();

    public Model(String ad,Marka marka,Kategori anaKategori,Set<Kategori> ekKategoriler){
        if(ad == null || ad.isBlank()){
            throw new IllegalArgumentException("Model adi bos olamaz!");
        }
        this.ad = ad.trim();
        if(marka == null){
            throw new IllegalArgumentException("Hata! Marka nesnesi bos!");
        }
        this.marka = marka;
        if(anaKategori == null){
            throw new IllegalArgumentException("Hata! Kategori nesnesi bos!");
        }
        this.anaKategori = anaKategori;
        if(ekKategoriler != null){
            for(Kategori k : ekKategoriler){
                if(k == null){
                    throw new IllegalArgumentException("Ek kategorilerde null eleman olamaz!");
                }
            }
        }
        this.kategoriler.add(anaKategori);
        if(ekKategoriler != null){
            this.kategoriler.addAll(ekKategoriler);
        }
    }
    public Set<Kategori> getKategoriler(){
        return Collections.unmodifiableSet(kategoriler);
    }
    public String getAd(){
        return this.ad;
    }
    public Marka getMarka(){
        return this.marka;
    }
    public Kategori getAnaKategori(){
        return this.anaKategori;
    }
    public boolean kategorideMi(Kategori k){
        if(k == null){
            return false;
        }
        for(Kategori kat : kategoriler){
            if(kat.altinda(k)){
                return true;
            }
        }
        return false;
    }
    @Override 
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Kategori k : kategoriler){
            if(sb.length() > 0){
                sb.append(", ");
            }
            sb.append(k.getAd());
        }
        return "Model{marka=" + marka.getAd() + ", ad=" + ad + ", anaKategori=" + anaKategori.getAd() + ", kategoriler=[" + sb + "]";
    }
    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Model diger = (Model) o;
        return this.marka.equals(diger.marka) && this.ad.equals(diger.ad);
    }
    @Override 
    public int hashCode(){
        return Objects.hash(marka,ad.toLowerCase());
    }
}
