package com.aracansiklopedisi.model;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class OzellikDegeri {
    private static final Pattern SAYI_DESENI = Pattern.compile("-?\\d+(\\.\\d+)?");
    private final Varyant varyant;
    private final OzellikTanimi tanim;
    private final String deger;

    public OzellikDegeri(Varyant varyant,OzellikTanimi tanim,String deger) {
        if(varyant == null){
            throw new IllegalArgumentException("Varyant bos olamaz!");
        }
        if(tanim == null){
            throw new IllegalArgumentException("Ozellik tanimi bos olamaz!");
        }
        if(deger == null || deger.isBlank()){
            throw new IllegalArgumentException("Ozellik degeri bos olamaz!");
        }
        if(!varyant.getModel().kategorideMi(tanim.getKategori())){
            throw new IllegalArgumentException("'" + tanim.getAd() + "' ozelligi bu varyantin kategorisinde gecerli degil: " + tanim.getKategori().getAd());
        }
        this.varyant = varyant;
        this.tanim = tanim;
        this.deger = degerDogrulaVeDuzenle(tanim.getTip(),deger.trim());
    }
    private static String degerDogrulaVeDuzenle(OzellikTipi tip,String deger){
        return switch(tip) {
            case SAYI -> {
                String sayi = deger.replace(',', '.');
                if(!SAYI_DESENI.matcher(sayi).matches()){
                    throw new IllegalArgumentException("Sayi bekleniyordu: " + deger);
                }
                if(Double.isInfinite(Double.parseDouble(sayi))){
                    throw new IllegalArgumentException("Sayi cok buyuk: " + deger);
                }
                yield sayi;
            }
            case EVET_HAYIR -> {
                String k = deger.toLowerCase(Locale.ROOT);
                if(k.equals("evet")){
                    yield "evet";
                }
                if(k.equals("hayir") || k.equals("hay\u0131r")){
                    yield "hayir";
                }
                throw new IllegalArgumentException("'evet' ya da 'hayir' bekleniyordu: " + deger);
            }
            case METIN -> deger;
        };
    }
    public Varyant getVaryant(){
        return varyant;
    }
    public OzellikTanimi getTanim(){
        return tanim;
    }
    public String getDeger(){
        return deger;
    }
    public double getSayiDegeri(){
        if(tanim.getTip() != OzellikTipi.SAYI){
            throw new IllegalStateException("Bu ozellik SAYI tipinde degil: " + tanim.getAd());
        }
        return Double.parseDouble(deger);
    }
    public boolean getEvetHayirDegeri(){
        if(tanim.getTip() != OzellikTipi.EVET_HAYIR){
            throw new IllegalStateException("Bu ozellik EVET_HAYIR tipinde degil: " + tanim.getAd());
        }
        return deger.equals("evet");
    }
    @Override 
    public String toString() {
        String birim = tanim.getBirim().map(b -> " " + b).orElse("");
        return "OzellikDegeri{" + varyant.getModel().getAd() + " " + varyant.getAd()
                + " -> " + tanim.getAd() + "=" + deger + birim + "}";
    }
    @Override 
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        OzellikDegeri diger = (OzellikDegeri) o;
        return this.varyant.equals(diger.varyant) && this.tanim.equals(diger.tanim);
    }
    @Override 
    public int hashCode(){
        return Objects.hash(varyant,tanim);
    }
}
