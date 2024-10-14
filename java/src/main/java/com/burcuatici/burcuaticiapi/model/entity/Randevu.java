package com.burcuatici.burcuaticiapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "randevu")
public class Randevu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hizmet_id")
    private Long hizmet;

    @Column(name = "calisan_id")
    private Long calisan;

    @Column(name = "musteri_id")
    private Long musteri;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Istanbul")
    @Column(name = "randevu_tarihi")
    private LocalDateTime randevuTarih;

    @Column(name = "randevu_aciklama")
    private String randevuAciklama;

    @Column(name = "is_pasive")
    private Boolean isPasive = false;

    public Randevu(Long hizmet, Long calisan, Long musteri, LocalDateTime randevuTarih,String randevuAciklama, Boolean isPasive) {
        this.hizmet = hizmet;
        this.calisan = calisan;
        this.musteri = musteri;
        this.randevuTarih = randevuTarih;
        this.randevuAciklama = randevuAciklama;
        this.isPasive = isPasive;
    }

    public Randevu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHizmet() {
        return hizmet;
    }

    public void setHizmet(Long hizmet) {
        this.hizmet = hizmet;
    }

    public Long getCalisan() {
        return calisan;
    }

    public void setCalisan(Long calisan) {
        this.calisan = calisan;
    }

    public Long getMusteri() {
        return musteri;
    }

    public void setMusteri(Long musteri) {
        this.musteri = musteri;
    }

    public LocalDateTime getRandevuTarih() {
        return randevuTarih;
    }

    public void setRandevuTarih(LocalDateTime randevuTarih) {
        this.randevuTarih = randevuTarih;
    }

    public String getRandevuAciklama() {
        return randevuAciklama;
    }

    public void setRandevuAciklama(String randevuAciklama) {
        this.randevuAciklama = randevuAciklama;
    }

    public Boolean getPasive() {
        return isPasive;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }
}
