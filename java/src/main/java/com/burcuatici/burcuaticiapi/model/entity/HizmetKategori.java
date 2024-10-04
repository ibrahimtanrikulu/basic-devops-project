package com.burcuatici.burcuaticiapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hizmet_kategori")
public class HizmetKategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hizmet_kategori_adi", nullable = false)
    private String hizmetKategoriAdi;

    @OneToMany(mappedBy = "hizmetKategori")
    @JsonManagedReference
    private Set<Hizmet> hizmetler = new HashSet<>();

    @Column(name = "is_pasive")
    @JsonIgnore
    private Boolean isPasive = false;

    public HizmetKategori() {
    }

    public HizmetKategori(String hizmetKategoriAdi) {
        this.hizmetKategoriAdi = hizmetKategoriAdi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHizmetKategoriAdi() {
        return hizmetKategoriAdi;
    }

    public void setHizmetKategoriAdi(String hizmetKategoriAdi) {
        this.hizmetKategoriAdi = hizmetKategoriAdi;
    }

    public Set<Hizmet> getHizmetler() {
        return hizmetler;
    }

    public void setHizmetler(Set<Hizmet> hizmetler) {
        this.hizmetler = hizmetler;
    }

    public void setPasive(Boolean pasive) {
        isPasive = pasive;
    }
}
