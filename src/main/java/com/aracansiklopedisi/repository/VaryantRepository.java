package com.aracansiklopedisi.repository;

import java.util.List;
import java.util.Optional;

import com.aracansiklopedisi.model.Model;
import com.aracansiklopedisi.model.Varyant;
import com.aracansiklopedisi.model.YakitTipi;

public interface VaryantRepository {
    void ekle(Varyant varyant);
    Optional<Varyant> bul(Model model,String ad);
    List<Varyant> hepsi();
    List<Varyant> modeleGore(Model model);
    List<Varyant> yakitTipineGore(YakitTipi yakitTipi);
}
