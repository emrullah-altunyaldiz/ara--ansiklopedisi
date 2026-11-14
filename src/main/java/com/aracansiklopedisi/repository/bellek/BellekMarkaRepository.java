package com.aracansiklopedisi.repository.bellek;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.aracansiklopedisi.model.Marka;
import com.aracansiklopedisi.repository.MarkaRepository;

public class BellekMarkaRepository implements MarkaRepository{
    private final Map<String, Marka> markalar = new LinkedHashMap<>();
    @Override
    public void ekle(Marka marka){
        if(marka == null){
            throw new IllegalArgumentException("Eklenecek marka bos olamaz!");
        }
        String anahtar = anahtarUret(marka.getAd());
        if(markalar.containsKey(anahtar)){
            throw new IllegalArgumentException("Bu marka zaten kayitli: " + marka.getAd());
        }
        markalar.put(anahtar, marka);
    }
    @Override 
    public Optional<Marka> adaGoreBul(String ad){
        if(ad == null){
            return Optional.empty();
        }
        Marka bulunan = markalar.get(anahtarUret(ad));
        return Optional.ofNullable(bulunan);
    }
    @Override 
    public List<Marka> hepsi(){
        return List.copyOf(markalar.values());
    }
    @Override 
    public List<Marka> ulkeyeGore(String ulke){
        if(ulke == null){
            return List.of();
        }
        List<Marka> sonuc = new ArrayList<>();
        for(Marka m: markalar.values()){
            if(m.getUlke().equalsIgnoreCase(ulke)){
                sonuc.add(m);
            }
        }
        return List.copyOf(sonuc);
    }
    private static String anahtarUret(String ad){
        return ad.trim().toLowerCase(Locale.ROOT);
    }
}
