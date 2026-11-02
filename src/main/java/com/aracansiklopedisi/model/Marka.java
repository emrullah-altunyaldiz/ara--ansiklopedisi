package com.aracansiklopedisi.model;

import java.time.Year;

public class Marka {
    private static final int EN_ESKI_YIL = 1800;
    private final String ad;
    private final String ulke;
    private final int kurulusYili;

    public Marka(String ad,String ulke,int kurulusYili) {
        if(ad == null || ad.isBlank()){
            throw new IllegalArgumentException("Marka adi bos olamaz!");
        }
        this.ad = ad.trim();
        if(ulke == null || ulke.isBlank()){
            throw new IllegalArgumentException("Ulke bos olamaz!");
        }
        this.ulke = ulke.trim();
        int buYil = Year.now().getValue();
        if(kurulusYili < EN_ESKI_YIL || kurulusYili > buYil){
            throw new IllegalArgumentException("Kurulus yili " + EN_ESKI_YIL + " ile " + buYil + " arasinda olamaz : " + kurulusYili);
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
