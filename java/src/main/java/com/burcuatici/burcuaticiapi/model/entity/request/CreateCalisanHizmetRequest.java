package com.burcuatici.burcuaticiapi.model.entity.request;

import java.util.List;

public class CreateCalisanHizmetRequest {

    private List<Long> hizmetIds;
    private Long calisanId;
    private Long hizmetKategoriId;

    public List<Long> getHizmetIds() {
        return hizmetIds;
    }

    public void setHizmetIds(List<Long> hizmetIds) {
        this.hizmetIds = hizmetIds;
    }

    public Long getCalisanId() {
        return calisanId;
    }

    public void setCalisanId(Long calisanId) {
        this.calisanId = calisanId;
    }

    public Long getHizmetKategoriId() {
        return hizmetKategoriId;
    }

    public void setHizmetKategoriId(Long hizmetKategoriId) {
        this.hizmetKategoriId = hizmetKategoriId;
    }
}