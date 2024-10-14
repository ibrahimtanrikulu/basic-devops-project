package com.burcuatici.burcuaticiapi.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "yeni_hizmet")
public class YeniHizmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hizmet_kategori_id")
    private Long hizmetKategoriID;

    @Column(name = "hizmet_id")
    private Long hizmetID;

    @Column(name = "yeni_hizmet_adi")
    private String yeniHizmetAdi;

    @Column(name = "ortalama_islem_suresi")
    private Integer ortalamaIslemSuresi;

    @Column(name = "hizmet_aciklama")
    private String aciklama;

    @Column(name = "is_pasive")
    private Boolean isPasive;

    @Column(name = "calisan_id_dizisi")
    private String calisanIds;

    public YeniHizmet(Long hizmetKategoriID, Long hizmetID, String yeniHizmetAdi, String aciklama, Integer ortalamaIslemSuresi, Boolean isPasive, String calisanIds) {
        this.hizmetKategoriID = hizmetKategoriID;
        this.hizmetID = hizmetID;
        this.yeniHizmetAdi = yeniHizmetAdi;
        this.aciklama = aciklama;
        this.ortalamaIslemSuresi = ortalamaIslemSuresi;
        this.isPasive = isPasive;
        this.calisanIds = calisanIds;

    }

    public YeniHizmet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHizmetKategoriID() {
        return hizmetKategoriID;
    }

    public void setHizmetKategoriID(Long hizmetKategoriID) {
        this.hizmetKategoriID = hizmetKategoriID;
    }

    public Long getHizmetID() {
        return hizmetID;
    }

    public void setHizmetID(Long hizmetID) {
        this.hizmetID = hizmetID;
    }

    public String getYeniHizmetAdi() {
        return yeniHizmetAdi;
    }

    public void setYeniHizmetAdi(String yeniHizmetAdi) {
        this.yeniHizmetAdi = yeniHizmetAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Integer getOrtalamaIslemSuresi() {
        return ortalamaIslemSuresi;
    }

    public void setOrtalamaIslemSuresi(Integer ortalamaIslemSuresi) {
        this.ortalamaIslemSuresi = ortalamaIslemSuresi;
    }

    public Boolean getPasive() {
        return isPasive;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }

    public String getCalisanIds() {
        return calisanIds;
    }

    public void setCalisanIds(String calisanIds) {
        this.calisanIds = calisanIds;
    }
}
