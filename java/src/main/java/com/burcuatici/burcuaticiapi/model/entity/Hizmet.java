package com.burcuatici.burcuaticiapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "hizmet")
public class Hizmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hizmet_adi", nullable = false)
    private String hizmetAdi;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "hizmet_kategori_id", nullable = false)
    @JsonBackReference
    private HizmetKategori hizmetKategori;

    @Column(name = "is_pasive")
    private Boolean isPasive = false;

    public Hizmet(HizmetKategori hizmetKategori, String hizmetAdi) {
        this.hizmetKategori = hizmetKategori;
        this.hizmetAdi = hizmetAdi;
    }

    public Hizmet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHizmetAdi() {
        return hizmetAdi;
    }

    public void setHizmetAdi(String hizmetAdi) {
        this.hizmetAdi = hizmetAdi;
    }

    public HizmetKategori getHizmetKategori() {
        return hizmetKategori;
    }

    public void setHizmetKategori(HizmetKategori hizmetKategori) {
        this.hizmetKategori = hizmetKategori;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }
}
