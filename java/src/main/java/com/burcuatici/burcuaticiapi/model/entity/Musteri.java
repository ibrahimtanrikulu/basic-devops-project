package com.burcuatici.burcuaticiapi.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "musteri")
public class Musteri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "musteri_adi")
    private String musteriAdi;

    @Column(name = "musteri_soyadi")
    private String musteriSoyadi;

    @Column(name = "musteri_cinsiyet")
    private String musteriCinsiyet;

    @Column(name = "musteri_tel")
    private String musteriTelNo;

    @Column(name = "musteri_tel2")
    private String musteriTelNo2;

    @Column(name = "musteri_email")
    private String musteriEmail;

    @Column(name = "musteri_dogum_tarihi")
    private String musteriDogumGunu;

    @Column(name = "musteri_kara_liste")
    private boolean musteriKaraListe;

    @Column(name = "is_pasive")
    private Boolean isPasive = false;

    public Musteri(String musteriAdi, String musteriSoyadi, String musteriCinsiyet, String musteriTelNo, String musteriTelNo2, String musteriEmail, String musteriDogumGunu, boolean musteriKaraListe) {
        this.musteriAdi = musteriAdi;
        this.musteriSoyadi = musteriSoyadi;
        this.musteriCinsiyet = musteriCinsiyet;
        this.musteriTelNo = musteriTelNo;
        this.musteriTelNo2 = musteriTelNo2;
        this.musteriEmail = musteriEmail;
        this.musteriDogumGunu = musteriDogumGunu;
        this.musteriKaraListe = musteriKaraListe;
    }

    public Musteri() {
    }

    public static boolean checkMusteri(String karar) {
        if (karar.equals("gonder")) {
            return true;
        } else {
            return false;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public String getMusteriSoyadi() {
        return musteriSoyadi;
    }

    public void setMusteriSoyadi(String musteriSoyadi) {
        this.musteriSoyadi = musteriSoyadi;
    }

    public String getMusteriCinsiyet() {
        return musteriCinsiyet;
    }

    public void setMusteriCinsiyet(String musteriCinsiyet) {
        this.musteriCinsiyet = musteriCinsiyet;
    }

    public String getMusteriTelNo() {
        return musteriTelNo;
    }

    public void setMusteriTelNo(String musteriTelNo) {
        this.musteriTelNo = musteriTelNo;
    }

    public String getMusteriTelNo2() {
        return musteriTelNo2;
    }

    public void setMusteriTelNo2(String musteriTelNo2) {
        this.musteriTelNo2 = musteriTelNo2;
    }

    public String getMusteriEmail() {
        return musteriEmail;
    }

    public void setMusteriEmail(String musteriEmail) {
        this.musteriEmail = musteriEmail;
    }

    public String getMusteriDogumGunu() {
        return musteriDogumGunu;
    }

    public void setMusteriDogumGunu(String musteriDogumGunu) {
        this.musteriDogumGunu = musteriDogumGunu;
    }

    public boolean isMusteriKaraListe() {
        return musteriKaraListe;
    }

    public void setMusteriKaraListe(boolean musteriKaraListe) {
        this.musteriKaraListe = musteriKaraListe;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }
}
