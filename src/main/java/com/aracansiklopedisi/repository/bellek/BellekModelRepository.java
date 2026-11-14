package com.aracansiklopedisi.repository.bellek;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.aracansiklopedisi.model.Kategori;
import com.aracansiklopedisi.model.Marka;
import com.aracansiklopedisi.model.Model;
import com.aracansiklopedisi.repository.ModelRepository;

public class BellekModelRepository implements ModelRepository{
    private final Set<Model> modeller = new LinkedHashSet<>();

    @Override 
    public void ekle(Model model){
        if(model == null){
            throw new IllegalArgumentException("Eklenecek model bos olamaz!");
        }
        if(!modeller.add(model)){
            throw new IllegalArgumentException("Bu model zaten kayitli: " + model.getMarka().getAd() + " " + model.getAd());
        }
    }
    @Override 
    public Optional<Model> bul(Marka marka, String ad){
        if(marka == null || ad == null){
            return Optional.empty();
        }
        for(Model m : modeller){
            if(m.getMarka().equals(marka) && m.getAd().equalsIgnoreCase(ad)){
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }
    @Override 
    public List<Model> hepsi(){
        return List.copyOf(modeller);
    }
    @Override 
    public List<Model> markayaGore(Marka marka){
        if(marka == null){
            return List.of();
        }
        List<Model> sonuc = new ArrayList<>();
        for(Model m : modeller){
            if(m.getMarka().equals(marka)){
                sonuc.add(m);
            }
        }
        return List.copyOf(sonuc);
    }
    @Override 
    public List<Model> kategorideKiModeller(Kategori kategori){
        if(kategori == null){
            return List.of();
        }
        List<Model> sonuc = new ArrayList<>();
        for(Model m : modeller){
            if(m.kategorideMi(kategori)){
                sonuc.add(m);
            }
        }
        return List.copyOf(sonuc);
    }
}
