package com.aracansiklopedisi.model;

import java.time.Year;

public class Marka {
    private final String ad;
    private final String ulke;
    private final int kurulusYili;
    private static final int EN_ESKİ_YIL = 1800;

    public Marka(String ad,String ulke,int kurulusYili) {
        if(ad == null || ad.isBlank()){
            throw new IllegalArgumentException("Marka adi bos olamaz!");
        }
        this.ad = ad.trim();
        if(ulke == null || ulke.isBlank()){
            throw new IllegalArgumentException("Ulke bos olamaz!");
        }
        this.ulke = ulke.trim();
        if(kurulusYili < EN_ESKİ_YIL || kurulusYili > Year.now().getValue()){
            throw new IllegalArgumentException("Kurulus yili 1800 ile bu yil arasinda olmalidir ");
        }
        this.kurulusYili = kurulusYili;
    }
    public String getAd(){
        return this.ad;
    }
    public String getUlke(){
        return this.ulke;
    }
    public int getKurulusYili(){
        return this.kurulusYili;
    }
    @Override 
    public String toString(){
        return "Marka{ad=" + ad + ", ulke=" + ulke + ", kurulusYili=" + kurulusYili + "}";
    }
    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        else if(o == null || getClass() != o.getClass()){
            return false;
        }
        Marka diger = (Marka) o;
        return this.ad.equalsIgnoreCase(diger.ad);
    }
    @Override
    public int hashCode(){
        return ad.toLowerCase().hashCode();
    }
}
