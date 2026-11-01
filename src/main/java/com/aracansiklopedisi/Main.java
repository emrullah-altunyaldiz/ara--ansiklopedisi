package com.aracansiklopedisi;

import com.aracansiklopedisi.model.Kategori;

public class Main {
    public static void main(String[] args) {
           Kategori kara = new Kategori("Kara", null);
Kategori otomobil = new Kategori("Otomobil", kara);
Kategori sedan = new Kategori("Sedan", otomobil);
new Kategori("SUV", otomobil);
Kategori motosiklet = new Kategori("Motosiklet", kara);
new Kategori("Arazi Araci", kara);

System.out.println(sedan.kokuBul().getAd());      // Kara
System.out.println(kara.kokuBul().getAd());       // Kara
System.out.println(sedan.altinda(kara));          // true
System.out.println(sedan.altinda(sedan));         // true
System.out.println(kara.altinda(sedan));          // false
System.out.println(motosiklet.altinda(otomobil)); // false
System.out.println(sedan.altinda(null));          // false
System.out.println(kara.tumTorunlar().size());    // 5
System.out.println(sedan.tumTorunlar().size());   // 0 
    
    }
}
