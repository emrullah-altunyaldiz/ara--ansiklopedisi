package com.aracansiklopedisi;

import java.util.HashSet;
import java.util.Set;

import com.aracansiklopedisi.model.Kategori;
import com.aracansiklopedisi.model.Marka;
import com.aracansiklopedisi.model.Model;

public class Main {
    public static void main(String[] args) {
        // 1) Kategori agaci
        Kategori kara = new Kategori("Kara", null);
        Kategori otomobil = new Kategori("Otomobil", kara);
        Kategori sedan = new Kategori("Sedan", otomobil);
        Kategori suv = new Kategori("SUV", otomobil);
        Kategori motosiklet = new Kategori("Motosiklet", kara);
        Kategori araziAraci = new Kategori("Arazi Araci", kara);

        kara.agaciYazdir();

        // 2) Markalar
        Marka toyota = new Marka("Toyota", "Japonya", 1937);
        Marka toyota2 = new Marka("toyota", "Japonya", 1937);

        // 3) Temel model + equals/hashCode testi
        Model a = new Model("Corolla", toyota, sedan, null);
        Model b = new Model("COROLLA", toyota2, sedan, null);

        System.out.println(a);
        System.out.println(a.equals(b));                  // true
        System.out.println(a.hashCode() == b.hashCode()); // true

        Set<Model> kume = new HashSet<>();
        kume.add(a);
        kume.add(b);
        System.out.println(kume.size());                  // 1

        // 4) Coklu kategori + defensive copy testi
        Set<Kategori> ek = new HashSet<>();
        ek.add(araziAraci);
        Model x = new Model("Ornek SUV", toyota, suv, ek);
        System.out.println(x.getKategoriler().size());    // 2
        ek.clear();
        System.out.println(x.getKategoriler().size());    // hala 2

        // 5) kategorideMi testi
        System.out.println(a.kategorideMi(kara));         // true
        System.out.println(a.kategorideMi(motosiklet));   // false
        System.out.println(x.kategorideMi(araziAraci));   // true

        // 6) Hata testleri
        try {
            new Marka("Test", "Tr", 2100);
            System.out.println("Hata firlatilmadi.");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }
        try {
            new Model("  ", toyota, sedan, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }
        try {
            new Model("Test", null, sedan, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }
        try {
            new Model("Test", toyota, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }
        try {
            Set<Kategori> bozuk = new HashSet<>();
            bozuk.add(null);
            new Model("Test", toyota, sedan, bozuk);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        // 7) Salt okunur kume testi
        try {
            a.getKategoriler().add(motosiklet);
        } catch (UnsupportedOperationException e) {
            System.out.println("Disaridan ekleme engellendi.");
        }
    }
}