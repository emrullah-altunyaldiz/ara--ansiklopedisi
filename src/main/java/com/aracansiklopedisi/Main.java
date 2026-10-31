package com.aracansiklopedisi;

import com.aracansiklopedisi.model.Kategori;

public class Main {
    public static void main(String[] args) {
        Kategori kara = new Kategori("Kara", null);
        Kategori otomobil = new Kategori("Otomobil", kara);
        new Kategori("Sedan", otomobil);

        // 1) trim: bastaki ve sondaki bosluklar silinir
        Kategori suv = new Kategori("   SUV   ", otomobil);
        System.out.println("[" + suv.getAd() + "]");

        // 2) throw: gecersiz ad, nesne olusmaz, hata firlar
        try {
            Kategori bos = new Kategori("   ", kara);
            System.out.println("Bu satir calismaz");
        } catch (IllegalArgumentException e) {
            System.out.println("Hata yakalandi: " + e.getMessage());
        }

        try {
            new Kategori(null, kara);
        } catch (IllegalArgumentException e) {
            System.out.println("Null icin de yakalandi: " + e.getMessage());
        }

        // 3) unmodifiableList: disaridan ekleme denemesi
        try {
            kara.getAltKategoriler().add(new Kategori("Hacker", null));
        } catch (UnsupportedOperationException e) {
            System.out.println("Listeye disaridan ekleme engellendi.");
        }

        // Okuma serbest
        System.out.println("Kara'nin alt kategori sayisi: " + kara.getAltKategoriler().size());

        kara.agaciYazdir();
    }
}
