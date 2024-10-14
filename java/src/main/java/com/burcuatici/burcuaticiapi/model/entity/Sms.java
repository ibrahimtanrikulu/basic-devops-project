package com.burcuatici.burcuaticiapi.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sms")
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sms_adi")
    private String smsAdi;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "tarihi")
    private LocalDateTime tarih;

    @Column(name = "is_pasive")
    private Boolean isPasive;

    public Sms(String smsAdi, String aciklama, LocalDateTime tarih, Boolean isPasive) {
        this.smsAdi = smsAdi;
        this.aciklama = aciklama;
        this.tarih = tarih;
        this.isPasive = isPasive;
    }

    public Sms() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmsAdi() {
        return smsAdi;
    }

    public void setSmsAdi(String smsAdi) {
        this.smsAdi = smsAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }

    public Boolean getPasive() {
        return isPasive;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }
}
