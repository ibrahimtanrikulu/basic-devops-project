package com.burcuatici.burcuaticiapi.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HizmetPair {

    @JsonProperty("id")
    Long id;
    @JsonProperty("hizmetAdi")
    String hizmetAdi;

    @JsonProperty("hizmetKategoriId")
    Long hizmetKategoriId;

    public HizmetPair(Long id, String hizmetAdi, Long hizmetKategoriId) {
        this.id = id;
        this.hizmetAdi = hizmetAdi;
        this.hizmetKategoriId = hizmetKategoriId;
    }
}