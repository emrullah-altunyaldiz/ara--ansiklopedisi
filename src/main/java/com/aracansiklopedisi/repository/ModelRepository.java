package com.aracansiklopedisi.repository;

import java.util.List;
import java.util.Optional;

import com.aracansiklopedisi.model.Kategori;
import com.aracansiklopedisi.model.Marka;
import com.aracansiklopedisi.model.Model;

public interface ModelRepository {
    void ekle(Model model);
    Optional<Model> bul(Marka marka, String ad);
    List<Model> hepsi();
    List<Model> markayaGore(Marka marka);
    List<Model> kategorideKiModeller(Kategori kategori);
}
