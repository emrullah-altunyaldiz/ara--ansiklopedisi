package com.aracansiklopedisi;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import com.aracansiklopedisi.model.Batarya;
import com.aracansiklopedisi.model.Kategori;
import com.aracansiklopedisi.model.Marka;
import com.aracansiklopedisi.model.Model;
import com.aracansiklopedisi.model.OzellikDegeri;
import com.aracansiklopedisi.model.OzellikTanimi;
import com.aracansiklopedisi.model.OzellikTipi;
import com.aracansiklopedisi.model.Varyant;
import com.aracansiklopedisi.model.YakitTipi;

public class Main {
    private static int basarili = 0;
    private static int basarisiz = 0;

    public static void main(String[] args) {
        kategoriTestleri();
        markaTestleri();
        modelTestleri();
        bataryaTestleri();
        yakitTipiTestleri();
        varyantTestleri();
        ozellikTestleri();
        ozetYazdir();
    }

    // ---------- Yardimci metotlar ----------

    private static void baslik(String ad) {
        System.out.println();
        System.out.println("--- " + ad + " ---");
    }

    private static void kontrol(String ad, boolean sonuc) {
        if (sonuc) {
            basarili++;
            System.out.println("[OK]   " + ad);
        } else {
            basarisiz++;
            System.out.println("[HATA] " + ad);
        }
    }

    private static void hataBeklenir(String ad, Runnable kod) {
        try {
            kod.run();
            basarisiz++;
            System.out.println("[HATA] " + ad + " -> hata firlatilmadi!");
        } catch (IllegalArgumentException e) {
            basarili++;
            System.out.println("[OK]   " + ad + " -> " + e.getMessage());
        }
    }

    private static void ozetYazdir() {
        System.out.println();
        System.out.println("=== OZET: " + basarili + " basarili, " + basarisiz + " basarisiz ===");
    }

    // ---------- Kategori ----------

    private static void kategoriTestleri() {
        baslik("Kategori testleri");
        Kategori kara = new Kategori("Kara", null);
        Kategori otomobil = new Kategori("Otomobil", kara);
        Kategori sedan = new Kategori("Sedan", otomobil);
        new Kategori("SUV", otomobil);
        Kategori motosiklet = new Kategori("Motosiklet", kara);
        new Kategori("Arazi Araci", kara);

        kara.agaciYazdir();

        kontrol("kara kok mu", kara.kokMu());
        kontrol("sedan kok degil", !sedan.kokMu());
        kontrol("sedan.kokuBul() == kara", sedan.kokuBul() == kara);
        kontrol("kara.kokuBul() == kara", kara.kokuBul() == kara);
        kontrol("sedan kara'nin altinda", sedan.altinda(kara));
        kontrol("sedan otomobilin altinda", sedan.altinda(otomobil));
        kontrol("sedan kendi altinda", sedan.altinda(sedan));
        kontrol("kara sedanin altinda degil", !kara.altinda(sedan));
        kontrol("motosiklet otomobilin altinda degil", !motosiklet.altinda(otomobil));
        kontrol("altinda(null) false", !sedan.altinda(null));
        kontrol("kara torun sayisi 5", kara.tumTorunlar().size() == 5);
        kontrol("sedan torun sayisi 0", sedan.tumTorunlar().isEmpty());
        kontrol("ad trim edilir", new Kategori("  Test  ", null).getAd().equals("Test"));

        hataBeklenir("bos kategori adi", () -> new Kategori("   ", null));
        hataBeklenir("null kategori adi", () -> new Kategori(null, null));

        try {
            kara.getAltKategoriler().add(new Kategori("Hacker", null));
            kontrol("alt liste disaridan degistirilemez", false);
        } catch (UnsupportedOperationException e) {
            kontrol("alt liste disaridan degistirilemez", true);
        }
    }

    // ---------- Marka ----------

    private static void markaTestleri() {
        baslik("Marka testleri");
        int buYil = Year.now().getValue();

        Marka toyota = new Marka("Toyota", "Japonya", 1937);
        System.out.println(toyota);
        kontrol("getAd", toyota.getAd().equals("Toyota"));
        kontrol("getUlke", toyota.getUlke().equals("Japonya"));
        kontrol("getKurulusYili", toyota.getKurulusYili() == 1937);
        kontrol("ad trim edilir", new Marka("  Ford  ", "ABD", 1903).getAd().equals("Ford"));

        hataBeklenir("bos ad", () -> new Marka("  ", "Japonya", 1937));
        hataBeklenir("null ulke", () -> new Marka("Test", null, 1937));
        hataBeklenir("yil 1799", () -> new Marka("Test", "TR", 1799));
        hataBeklenir("yil bu yil + 1", () -> new Marka("Test", "TR", buYil + 1));
        hataBeklenir("yil 2100", () -> new Marka("Test", "TR", 2100));
        kontrol("yil 1800 gecerli", new Marka("Test", "TR", 1800).getKurulusYili() == 1800);
        kontrol("yil bu yil gecerli", new Marka("Test", "TR", buYil).getKurulusYili() == buYil);

        Marka kucuk = new Marka("toyota", "Japonya", 1937);
        kontrol("equals buyuk/kucuk harf duyarsiz", toyota.equals(kucuk));
        kontrol("hashCode esit", toyota.hashCode() == kucuk.hashCode());
        kontrol("equals null false", !toyota.equals(null));
        kontrol("equals farkli marka false", !toyota.equals(new Marka("Ford", "ABD", 1903)));

        Set<Marka> kume = new HashSet<>();
        kume.add(toyota);
        kume.add(kucuk);
        kontrol("HashSet boyutu 1", kume.size() == 1);
    }

    // ---------- Model ----------

    private static void modelTestleri() {
        baslik("Model testleri");
        Kategori kara = new Kategori("Kara", null);
        Kategori otomobil = new Kategori("Otomobil", kara);
        Kategori sedan = new Kategori("Sedan", otomobil);
        Kategori suv = new Kategori("SUV", otomobil);
        Kategori motosiklet = new Kategori("Motosiklet", kara);
        Kategori araziAraci = new Kategori("Arazi Araci", kara);

        Marka toyota = new Marka("Toyota", "Japonya", 1937);
        Marka toyota2 = new Marka("toyota", "Japonya", 1937);

        Model corolla = new Model("Corolla", toyota, sedan, null);
        Model corolla2 = new Model("COROLLA", toyota2, sedan, null);
        System.out.println(corolla);

        kontrol("getAd", corolla.getAd().equals("Corolla"));
        kontrol("getMarka", corolla.getMarka() == toyota);
        kontrol("getAnaKategori", corolla.getAnaKategori() == sedan);
        kontrol("ek kategorisiz model 1 kategori", corolla.getKategoriler().size() == 1);

        kontrol("equals", corolla.equals(corolla2));
        kontrol("hashCode esit", corolla.hashCode() == corolla2.hashCode());
        Set<Model> kume = new HashSet<>();
        kume.add(corolla);
        kume.add(corolla2);
        kontrol("HashSet boyutu 1", kume.size() == 1);
        kontrol("farkli marka ayni ad esit degil",
                !corolla.equals(new Model("Corolla", new Marka("Ford", "ABD", 1903), sedan, null)));

        Set<Kategori> ek = new HashSet<>();
        ek.add(araziAraci);
        Model ornekSuv = new Model("Ornek SUV", toyota, suv, ek);
        kontrol("coklu kategori 2 eleman", ornekSuv.getKategoriler().size() == 2);
        ek.clear();
        kontrol("defensive copy: dis kume silinince etkilenmez", ornekSuv.getKategoriler().size() == 2);

        kontrol("corolla kara kategorisinde", corolla.kategorideMi(kara));
        kontrol("corolla motosiklet kategorisinde degil", !corolla.kategorideMi(motosiklet));
        kontrol("suv modeli arazi kategorisinde", ornekSuv.kategorideMi(araziAraci));
        kontrol("kategorideMi(null) false", !corolla.kategorideMi(null));

        hataBeklenir("bos model adi", () -> new Model("  ", toyota, sedan, null));
        hataBeklenir("null marka", () -> new Model("Test", null, sedan, null));
        hataBeklenir("null ana kategori", () -> new Model("Test", toyota, null, null));
        hataBeklenir("ek kategoride null eleman", () -> {
            Set<Kategori> bozuk = new HashSet<>();
            bozuk.add(null);
            new Model("Test", toyota, sedan, bozuk);
        });

        try {
            corolla.getKategoriler().add(motosiklet);
            kontrol("kategori kumesi disaridan degistirilemez", false);
        } catch (UnsupportedOperationException e) {
            kontrol("kategori kumesi disaridan degistirilemez", true);
        }
    }

    // ---------- Batarya ----------

    private static void bataryaTestleri() {
        baslik("Batarya testleri");
        Batarya b1 = new Batarya(82.5, 450);
        Batarya b2 = new Batarya(82.5, 450);
        System.out.println(b1);

        kontrol("getKapasiteKwh", b1.getKapasiteKwh() == 82.5);
        kontrol("getMenzilKm", b1.getMenzilKm() == 450);
        kontrol("equals", b1.equals(b2));
        kontrol("hashCode esit", b1.hashCode() == b2.hashCode());
        kontrol("farkli batarya esit degil", !b1.equals(new Batarya(60, 400)));

        hataBeklenir("kapasite 0", () -> new Batarya(0, 450));
        hataBeklenir("kapasite -5", () -> new Batarya(-5, 450));
        hataBeklenir("kapasite NaN", () -> new Batarya(Double.NaN, 450));
        hataBeklenir("kapasite sonsuz", () -> new Batarya(Double.POSITIVE_INFINITY, 450));
        hataBeklenir("kapasite 300.1 (ust sinir asildi)", () -> new Batarya(300.1, 450));
        hataBeklenir("menzil 0", () -> new Batarya(82.5, 0));
        hataBeklenir("menzil -10", () -> new Batarya(82.5, -10));

        kontrol("kapasite 0.1 gecerli", new Batarya(0.1, 450).getKapasiteKwh() == 0.1);
        kontrol("kapasite 300 gecerli", new Batarya(300, 450).getKapasiteKwh() == 300);
    }

    // ---------- YakitTipi ----------

    private static void yakitTipiTestleri() {
        baslik("YakitTipi testleri");
        kontrol("5 yakit tipi var", YakitTipi.values().length == 5);
        kontrol("ELEKTRIK mevcut", YakitTipi.valueOf("ELEKTRIK") == YakitTipi.ELEKTRIK);
    }

    // ---------- Varyant ----------

    private static void varyantTestleri() {
        baslik("Varyant testleri");
        int buYil = Year.now().getValue();

        Kategori kara = new Kategori("Kara", null);
        Kategori otomobil = new Kategori("Otomobil", kara);
        Kategori sedan = new Kategori("Sedan", otomobil);
        Marka toyota = new Marka("Toyota", "Japonya", 1937);
        Model corolla = new Model("Corolla", toyota, sedan, null);
        Batarya bat = new Batarya(82.5, 450);

        Varyant dizel = new Varyant(corolla, "1.6 Dizel", 2019, null, YakitTipi.DIZEL, 110, 1300, null);
        System.out.println(dizel);
        kontrol("uretimdeMi true", dizel.uretimdeMi());
        kontrol("bitisYili bos", dizel.getBitisYili().isEmpty());
        kontrol("dizelde batarya yok", dizel.getBatarya().isEmpty());
        kontrol("ad trim edilir",
                new Varyant(corolla, "  1.8  ", 2019, null, YakitTipi.BENZIN, 120, 1300, null).getAd().equals("1.8"));

        Varyant elektrikli = new Varyant(corolla, "EV", 2022, 2024, YakitTipi.ELEKTRIK, 200, 1800, bat);
        kontrol("elektrikli batarya var", elektrikli.getBatarya().isPresent());
        kontrol("elektrikli uretimde degil", !elektrikli.uretimdeMi());
        kontrol("bitisYili 2024", elektrikli.getBitisYili().get() == 2024);

        hataBeklenir("ELEKTRIK + batarya null",
                () -> new Varyant(corolla, "EV", 2022, null, YakitTipi.ELEKTRIK, 200, 1800, null));
        hataBeklenir("DIZEL + batarya dolu",
                () -> new Varyant(corolla, "X", 2022, null, YakitTipi.DIZEL, 100, 1300, bat));
        hataBeklenir("BENZIN + batarya dolu",
                () -> new Varyant(corolla, "X", 2022, null, YakitTipi.BENZIN, 100, 1300, bat));
        hataBeklenir("LPG + batarya dolu",
                () -> new Varyant(corolla, "X", 2022, null, YakitTipi.LPG, 100, 1300, bat));
        kontrol("HIBRIT bataryasiz gecerli",
                new Varyant(corolla, "H", 2022, null, YakitTipi.HIBRIT, 120, 1400, null).getBatarya().isEmpty());
        kontrol("HIBRIT bataryali gecerli",
                new Varyant(corolla, "H2", 2022, null, YakitTipi.HIBRIT, 120, 1400, bat).getBatarya().isPresent());

        hataBeklenir("bitis < baslangic",
                () -> new Varyant(corolla, "X", 2020, 2019, YakitTipi.DIZEL, 100, 1300, null));
        hataBeklenir("bitis gelecekte",
                () -> new Varyant(corolla, "X", 2020, buYil + 1, YakitTipi.DIZEL, 100, 1300, null));
        kontrol("bitis = baslangic gecerli",
                new Varyant(corolla, "Y", 2020, 2020, YakitTipi.DIZEL, 100, 1300, null).getBitisYili().get() == 2020);

        hataBeklenir("baslangic 1879",
                () -> new Varyant(corolla, "X", 1879, null, YakitTipi.DIZEL, 100, 1300, null));
        kontrol("baslangic 1880 gecerli",
                new Varyant(corolla, "A", 1880, null, YakitTipi.DIZEL, 100, 1300, null).getBaslangicYili() == 1880);
        kontrol("baslangic bu yil gecerli",
                new Varyant(corolla, "B", buYil, null, YakitTipi.DIZEL, 100, 1300, null).getBaslangicYili() == buYil);
        hataBeklenir("baslangic bu yil + 1",
                () -> new Varyant(corolla, "X", buYil + 1, null, YakitTipi.DIZEL, 100, 1300, null));

        hataBeklenir("guc 0", () -> new Varyant(corolla, "X", 2020, null, YakitTipi.DIZEL, 0, 1300, null));
        hataBeklenir("guc negatif", () -> new Varyant(corolla, "X", 2020, null, YakitTipi.DIZEL, -5, 1300, null));
        hataBeklenir("agirlik 0", () -> new Varyant(corolla, "X", 2020, null, YakitTipi.DIZEL, 100, 0, null));

        hataBeklenir("null model", () -> new Varyant(null, "X", 2020, null, YakitTipi.DIZEL, 100, 1300, null));
        hataBeklenir("bos ad", () -> new Varyant(corolla, "  ", 2020, null, YakitTipi.DIZEL, 100, 1300, null));
        hataBeklenir("null yakit tipi", () -> new Varyant(corolla, "X", 2020, null, null, 100, 1300, null));

        Varyant dizel2 = new Varyant(corolla, "1.6 DIZEL", 2019, null, YakitTipi.DIZEL, 110, 1300, null);
        kontrol("equals (ad harf farki)", dizel.equals(dizel2));
        kontrol("hashCode esit", dizel.hashCode() == dizel2.hashCode());
        kontrol("farkli yakit esit degil",
                !dizel.equals(new Varyant(corolla, "1.6 Dizel", 2019, null, YakitTipi.BENZIN, 110, 1300, null)));
    }
    private static void ozellikTestleri() {
        baslik("Ozellik testleri");

        // Test verisi: kategori agaci, marka, model, varyant
        Kategori kara = new Kategori("Kara", null);
        Kategori otomobil = new Kategori("Otomobil", kara);
        Kategori sedan = new Kategori("Sedan", otomobil);
        Kategori motosiklet = new Kategori("Motosiklet", kara);
        Marka toyota = new Marka("Toyota", "Japonya", 1937);
        Model corolla = new Model("Corolla", toyota, sedan, null);
        Varyant dizel = new Varyant(corolla, "1.6 Dizel", 2019, null, YakitTipi.DIZEL, 110, 1300, null);

        // Tanimlar
        OzellikTanimi kapi = new OzellikTanimi("Kapi Sayisi", "adet", OzellikTipi.SAYI, otomobil);
        OzellikTanimi hacim = new OzellikTanimi("Motor Hacmi", "litre", OzellikTipi.SAYI, otomobil);
        OzellikTanimi klima = new OzellikTanimi("Klima", null, OzellikTipi.EVET_HAYIR, otomobil);
        OzellikTanimi renk = new OzellikTanimi("Renk", "  ", OzellikTipi.METIN, otomobil);
        OzellikTanimi motorCc = new OzellikTanimi("Motor Hacmi CC", "cc", OzellikTipi.SAYI, motosiklet);
        OzellikTanimi karaOzelligi = new OzellikTanimi("Yol Tipi", null, OzellikTipi.METIN, kara);
        System.out.println(kapi);

        // --- OzellikTanimi ---
        kontrol("tanim ad trim/getAd", new OzellikTanimi("  Agirlik ", "kg", OzellikTipi.SAYI, kara).getAd().equals("Agirlik"));
        kontrol("birim var", kapi.getBirim().orElse("").equals("adet"));
        kontrol("birim yok (null)", klima.getBirim().isEmpty());
        kontrol("bos birim yok sayilir", renk.getBirim().isEmpty());
        kontrol("tanim equals (ad harf farki)",
            kapi.equals(new OzellikTanimi("KAPI SAYISI", null, OzellikTipi.SAYI, otomobil)));
        kontrol("tanim hashCode esit",
            kapi.hashCode() == new OzellikTanimi("KAPI SAYISI", null, OzellikTipi.SAYI, otomobil).hashCode());
        kontrol("farkli kategori esit degil",
            !kapi.equals(new OzellikTanimi("Kapi Sayisi", null, OzellikTipi.SAYI, sedan)));
        hataBeklenir("tanim bos ad", () -> new OzellikTanimi("  ", null, OzellikTipi.SAYI, otomobil));
        hataBeklenir("tanim null tip", () -> new OzellikTanimi("X", null, null, otomobil));
        hataBeklenir("tanim null kategori", () -> new OzellikTanimi("X", null, OzellikTipi.SAYI, null));

        // --- OzellikDegeri: gecerli durumlar ---
        OzellikDegeri kapiDegeri = new OzellikDegeri(dizel, kapi, "5");
        System.out.println(kapiDegeri);
        kontrol("sayi degeri 5.0", kapiDegeri.getSayiDegeri() == 5.0);
        kontrol("virgullu sayi 1,6", new OzellikDegeri(dizel, hacim, "1,6").getSayiDegeri() == 1.6);
        kontrol("negatif sayi gecerli", new OzellikDegeri(dizel, kapi, "-3").getSayiDegeri() == -3.0);
        kontrol("deger trim edilir", new OzellikDegeri(dizel, kapi, "  4 ").getDeger().equals("4"));
        kontrol("evet", new OzellikDegeri(dizel, klima, "EVET").getEvetHayirDegeri());
        kontrol("hayir", !new OzellikDegeri(dizel, klima, "Hayir").getEvetHayirDegeri());
        kontrol("hayir (turkce ı)", !new OzellikDegeri(dizel, klima, "hay\u0131r").getEvetHayirDegeri());
        kontrol("metin degeri", new OzellikDegeri(dizel, renk, "Kirmizi").getDeger().equals("Kirmizi"));
        kontrol("MIRAS: ust kategori (Kara) tanimi sedan varyantina girilebilir",
            new OzellikDegeri(dizel, karaOzelligi, "Asfalt").getDeger().equals("Asfalt"));

        // --- OzellikDegeri: hatali durumlar ---
        hataBeklenir("sayi yerine harf", () -> new OzellikDegeri(dizel, kapi, "abc"));
        hataBeklenir("sayi: iki nokta", () -> new OzellikDegeri(dizel, kapi, "1.2.3"));
        hataBeklenir("evet/hayir yerine belki", () -> new OzellikDegeri(dizel, klima, "belki"));
        hataBeklenir("bos deger", () -> new OzellikDegeri(dizel, kapi, "  "));
        hataBeklenir("null deger", () -> new OzellikDegeri(dizel, kapi, null));
        hataBeklenir("null varyant", () -> new OzellikDegeri(null, kapi, "5"));
        hataBeklenir("null tanim", () -> new OzellikDegeri(dizel, null, "5"));
        hataBeklenir("KATEGORI: motosiklet ozelligi sedan varyantina girilemez",
            () -> new OzellikDegeri(dizel, motorCc, "600"));

        // --- Tipli getter yanlis kullanim: IllegalStateException bekleniyor ---
        try {
            kapiDegeri.getEvetHayirDegeri();
            kontrol("SAYI ozellige getEvetHayirDegeri hata verir", false);
        } catch (IllegalStateException e) {
            kontrol("SAYI ozellige getEvetHayirDegeri hata verir", true);
        }

        // --- equals/hashCode ---
        OzellikDegeri kapiDegeri2 = new OzellikDegeri(dizel, kapi, "3");
        kontrol("ayni varyant+tanim esit (deger farki onemsiz)", kapiDegeri.equals(kapiDegeri2));
        kontrol("hashCode esit", kapiDegeri.hashCode() == kapiDegeri2.hashCode());
        kontrol("farkli tanim esit degil", !kapiDegeri.equals(new OzellikDegeri(dizel, hacim, "5")));
    }
}