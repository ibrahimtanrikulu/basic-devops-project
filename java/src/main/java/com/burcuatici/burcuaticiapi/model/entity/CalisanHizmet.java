package com.burcuatici.burcuaticiapi.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CalisanHizmet {

    @JsonProperty("calisanId")
    Long calisanId;

    @JsonProperty("hizmetler")
    List<HizmetPair> hizmetler;

    @JsonProperty("hizmetKategoriId")
    Long hizmetKategoriId;

    public CalisanHizmet(Long calisanId, List<Hizmet> hizmetler, HizmetKategori hizmetKategoriId) {
        this.calisanId = calisanId;
        this.hizmetler = hizmetler.stream()
                .map(hizmet -> new HizmetPair(
                                hizmet.getId(),
                                hizmet.getHizmetAdi(),
                                hizmet.getHizmetKategori().getId()
                        )
                )
                .toList();
        this.hizmetKategoriId = hizmetKategoriId.getId();
    }
}
