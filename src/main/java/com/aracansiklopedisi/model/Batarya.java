package com.aracansiklopedisi.model;

import java.util.Objects;

public class Batarya {
    private final double kapasiteKwh;
    private final int menzilKm;
    private static final double EN_YUKSEK_KAPASITE = 300.0;
    public Batarya(double kapasiteKwh , int menzilKm) {
        if(Double.isNaN(kapasiteKwh) || Double.isInfinite(kapasiteKwh) || kapasiteKwh <= 0 || kapasiteKwh > EN_YUKSEK_KAPASITE){
        throw new IllegalArgumentException("Kapasite 0 ile " + EN_YUKSEK_KAPASITE + " arasinda olmali: " + kapasiteKwh);
        }
        this.kapasiteKwh = kapasiteKwh;
        if(menzilKm <= 0) {
            throw new IllegalArgumentException("Menzil pozitif olmali: " + menzilKm);
        }
        this.menzilKm = menzilKm;
    }
    public double getKapasiteKwh(){
        return kapasiteKwh;
    }
    public int getMenzilKm(){
        return menzilKm; 
    }
    @Override 
    public String toString(){
        return "Batarya{kapasiteKwh=" + kapasiteKwh + ", menzilKm=" + menzilKm + "}";
    }
    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Batarya diger = (Batarya) o;
        return Double.compare(this.kapasiteKwh, diger.kapasiteKwh) == 0 && this.menzilKm == diger.menzilKm;
    }
    @Override 
    public int hashCode(){
        return Objects.hash(kapasiteKwh, menzilKm);
    }
}
