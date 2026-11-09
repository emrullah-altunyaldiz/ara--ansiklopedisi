package com.aracansiklopedisi.model;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class OzellikTanimi {
    private final String ad;
    private final String birim;
    private final OzellikTipi tip;
    private final Kategori kategori;

    public OzellikTanimi(String ad,String birim,OzellikTipi tip,Kategori kategori) {
        if(ad == null || ad.isBlank()){
            throw new IllegalArgumentException("Ozellik adi bos olamaz!");
        }
        if(tip == null){
            throw new IllegalArgumentException("Ozellik tipi bos olamaz!");
        }
        if(kategori == null){
            throw new IllegalArgumentException("Ozellik kategorisi bos olamaz!");
        }
        this.ad = ad.trim();
        this.tip = tip;
        this.kategori = kategori;
        this.birim = (birim == null || birim.isBlank()) ? null : birim.trim();
    }
    public String getAd(){
        return ad;
    }
    public Optional<String> getBirim(){
        return Optional.ofNullable(birim);
    }
    public OzellikTipi getTip(){
        return tip;
    }
    public Kategori getKategori(){
        return kategori;
    }
    @Override 
    public String toString(){
        return "OzellikTanimi{ad=" + ad + ", tip=" + tip + ", birim=" + (birim == null ? "yok" : birim) + ", kategori=" + kategori.getAd() + "}";
    }
    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        OzellikTanimi diger = (OzellikTanimi) o;
        return this.kategori == diger.kategori && this.ad.equalsIgnoreCase(diger.ad);
    }
    @Override
    public int hashCode(){
        return Objects.hash(kategori,ad.toLowerCase(Locale.ROOT));
    }
}
