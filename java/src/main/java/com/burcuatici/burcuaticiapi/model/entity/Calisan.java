package com.burcuatici.burcuaticiapi.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "calisan")
public class Calisan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calisan_adi")
    private String calisanAdi;

    @Column(name = "calisan_soyadi")
    private String calisanSoyad;

    @Column(name = "calisan_cinsiyet")
    private String calisanCinsiyet;

    @Column(name = "calisan_unvan")
    private String calisanUnvan;

    @Column(name = "calisan_tel")
    private String calisanTelNo;

    @Column(name = "calisan_email")
    private String calisanEmail;

    @Column(name = "is_pasive")
    private Boolean isPasive = false;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH
            })
    @JoinTable(
            name = "calisan_hizmet",
            joinColumns = @JoinColumn(name = "calisan_id"),
            inverseJoinColumns = @JoinColumn(name = "hizmet_id")
    )
    private Set<Hizmet> hizmetler = new HashSet<>();

    public Calisan(
            String calisanAdi,
            String calisanSoyad,
            String calisanCinsiyet,
            String calisanUnvan,
            String calisanTelNo,
            String calisanEmail,
            boolean isPasive
    ) {
        this.calisanAdi = calisanAdi;
        this.calisanSoyad = calisanSoyad;
        this.calisanCinsiyet = calisanCinsiyet;
        this.calisanUnvan = calisanUnvan;
        this.calisanTelNo = calisanTelNo;
        this.calisanEmail = calisanEmail;
        this.isPasive = isPasive;
    }

    public Calisan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalisanAdi() {
        return calisanAdi;
    }

    public void setCalisanAdi(String calisanAdi) {
        this.calisanAdi = calisanAdi;
    }

    public String getCalisanSoyad() {
        return calisanSoyad;
    }

    public void setCalisanSoyad(String calisanSoyad) {
        this.calisanSoyad = calisanSoyad;
    }

    public String getCalisanCinsiyet() {
        return calisanCinsiyet;
    }

    public void setCalisanCinsiyet(String calisanCinsiyet) {
        this.calisanCinsiyet = calisanCinsiyet;
    }

    public String getCalisanUnvan() {
        return calisanUnvan;
    }

    public void setCalisanUnvan(String calisanUnvan) {
        this.calisanUnvan = calisanUnvan;
    }

    public String getCalisanTelNo() {
        return calisanTelNo;
    }

    public void setCalisanTelNo(String calisanTelNo) {
        this.calisanTelNo = calisanTelNo;
    }

    public String getCalisanEmail() {
        return calisanEmail;
    }

    public void setCalisanEmail(String calisanEmail) {
        this.calisanEmail = calisanEmail;
    }

    public Set<Hizmet> getHizmetler() {
        return hizmetler;
    }

    public void setHizmetler(Set<Hizmet> hizmetler) {
        this.hizmetler = hizmetler;
    }

    public Boolean getPasive() {
        return isPasive != null && isPasive;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }
}