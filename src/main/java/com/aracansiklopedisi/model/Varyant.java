package com.aracansiklopedisi.model;

import java.time.Year;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Varyant {
    private static final int EN_ESKI_YIL = 1880;
    private static final int EN_YUKSEK_GUC_HP = 3000;
    private static final int EN_YUKSEK_AGIRLIK_KG = 100000;

    private final Model model;
    private final String ad;
    private final int baslangicYili;
    private final Integer bitisYili;
    private final YakitTipi yakitTipi;
    private final int gucHp;
    private final int agirlikKg;
    private final Batarya batarya;

    public Varyant(Model model,String ad,int baslangicYili,Integer bitisYili,YakitTipi yakitTipi,int gucHp,int agirlikKg,Batarya batarya) {
        if(model == null){
            throw new IllegalArgumentException("Model bos olamaz!");
        }
        this.model = model;
        if(ad == null || ad.isBlank()){
            throw new IllegalArgumentException("Varyant adi bos olamaz!");
        }
        this.ad = ad.trim();
        if(yakitTipi == null){
            throw new IllegalArgumentException("Yakit Tipi bos olamaz!");
        }
        this.yakitTipi = yakitTipi;
        int buYil = Year.now().getValue();
        if(baslangicYili < EN_ESKI_YIL || baslangicYili > buYil){
            throw new IllegalArgumentException("Baslangic yili " + EN_ESKI_YIL + " ile " + buYil + " arasinda olmali: " + baslangicYili);
        }
        this.baslangicYili = baslangicYili;
        if(bitisYili != null && (bitisYili < baslangicYili || bitisYili > buYil)){
            throw new IllegalArgumentException("Bitis yili " + baslangicYili + " ile " + buYil + " arasinda olmali: " + bitisYili);
        }
        this.bitisYili = bitisYili;
        if(gucHp <= 0 || gucHp > EN_YUKSEK_GUC_HP){
            throw new IllegalArgumentException("Guc 1 ile " + EN_YUKSEK_GUC_HP + " hp arasinda olmali: " + gucHp);
        }
        this.gucHp = gucHp;
        if(agirlikKg <= 0 || agirlikKg > EN_YUKSEK_AGIRLIK_KG){
            throw new IllegalArgumentException("Agirlik 1 ile " + EN_YUKSEK_AGIRLIK_KG + " kg arasinda olmali: " + agirlikKg);
        }
        this.agirlikKg = agirlikKg;
        String bataryaHatasi = switch (yakitTipi){
            case ELEKTRIK -> batarya == null ? "Elektrikli varyantta batarya zorunlu!" : null;
            case BENZIN, DIZEL, LPG -> batarya != null ? "Bu yakit tipinde batarya olamaz: " + yakitTipi : null;
            case HIBRIT -> null;
        };
        if(bataryaHatasi != null){
            throw new IllegalArgumentException(bataryaHatasi);
        }
        this.batarya = batarya;
    }
    public Model getModel(){
        return model;
    }
    public String getAd(){
        return ad;
    }
    public int getBaslangicYili(){
        return baslangicYili;
    }
    public Optional<Integer> getBitisYili(){
        return Optional.ofNullable(bitisYili);
    }
    public YakitTipi getYakitTipi(){
        return yakitTipi;
    }
    public int getGucHp(){
        return gucHp;
    }
    public int getAgirlikKg(){
        return agirlikKg;
    }
    public Optional<Batarya> getBatarya(){
        return Optional.ofNullable(batarya);
    }
    public boolean uretimdeMi(){
        return bitisYili == null;
    }
    @Override 
    public String toString(){
        String bitis = (bitisYili == null) ? "devam ediliyor" : String.valueOf(bitisYili);
        return "Varyant{model=" + model.getMarka().getAd() + " " + model.getAd() + ", ad=" + ad + ", yakit=" + yakitTipi + ", yillar=" + baslangicYili + "-" + bitis + ", gucHp=" + gucHp + ", agirlikKg=" + agirlikKg + ", batarya=" + (batarya == null ? "yok" : batarya) + "}";
    }
    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Varyant diger = (Varyant) o;
        return this.model.equals(diger.model) && this.ad.equalsIgnoreCase(diger.ad) && this.yakitTipi == diger.yakitTipi;
    }
    @Override
    public int hashCode() {
        return Objects.hash(model, ad.toLowerCase(Locale.ROOT), yakitTipi);
    }
}
