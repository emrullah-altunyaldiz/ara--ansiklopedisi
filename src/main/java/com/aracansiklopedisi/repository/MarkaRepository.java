package com.aracansiklopedisi.repository;

import java.util.List;
import java.util.Optional;

import com.aracansiklopedisi.model.Marka;

public interface MarkaRepository {
    void ekle(Marka marka);
    Optional<Marka> adaGoreBul(String ad);
    List<Marka> hepsi();
    List<Marka> ulkeyeGore();
}
