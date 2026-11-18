package com.aracansiklopedisi.repository.bellek;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.aracansiklopedisi.model.Model;
import com.aracansiklopedisi.model.Varyant;
import com.aracansiklopedisi.model.YakitTipi;
import com.aracansiklopedisi.repository.VaryantRepository;

public class BellekVaryantRepository implements VaryantRepository{
    private final Set<Varyant> varyantlar = new LinkedHashSet<>();

    @Override   
    public void ekle(Varyant varyant){
        if(varyant == null){
            throw new IllegalArgumentException("Eklenecek varyant bos olamaz!");
        }
        if(!varyantlar.add(varyant)){
            throw new IllegalArgumentException("Bu varyant zaten kayitli: " + varyant.getModel().getAd() + " " + varyant.getAd());
        }
    }

    @Override 
    public Optional<Varyant> bul(Model model,String ad){
        if(model == null || ad == null){
            return Optional.empty();
        }
        for(Varyant v: varyantlar){
            if(v.getModel().equals(model) && v.getAd().equalsIgnoreCase(ad)){
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    @Override 
    public List<Varyant> hepsi(){
        return List.copyOf(varyantlar);
    }

    @Override 
    public List<Varyant> modeleGore(Model model){
        if(model == null){
            return List.of();
        }
        List<Varyant> sonuc = new ArrayList<>();
        for(Varyant v: varyantlar){
            if(v.getModel().equals(model)){
                sonuc.add(v);
            }
        }
        return List.copyOf(sonuc);
    }

    @Override 
    public List<Varyant> yakitTipineGore(YakitTipi yakitTipi){
        if(yakitTipi == null){
            return List.of();
        }
        List<Varyant> sonuc = new ArrayList<>();
        for(Varyant v : varyantlar){
            if(v.getYakitTipi() == yakitTipi){
                sonuc.add(v);
            }
        }
        return List.copyOf(sonuc);
    }
}
