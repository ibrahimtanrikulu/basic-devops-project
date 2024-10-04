package com.burcuatici.burcuaticiapi.model.entity.response;

import com.burcuatici.burcuaticiapi.model.entity.Calisan;
import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.model.entity.HizmetPair;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public class CalisanResponse {

    @JsonProperty("calisanId")
    private Long calisanId;

    @JsonProperty("calisanAdi")
    private String calisanAdi;

    @JsonProperty("calisanSoyad")
    private String calisanSoyad;

    @JsonProperty("calisanCinsiyet")
    private String calisanCinsiyet;

    @JsonProperty("calisanUnvan")
    private String calisanUnvan;

    @JsonProperty("calisanTelNo")
    private String calisanTelNo;

    @JsonProperty("calisanEmail")
    private String calisanEmail;

    @JsonProperty("isPasive")
    private boolean isPasive;

    @JsonProperty("hizmetler")
    private List<HizmetPair> hizmetler;

    public CalisanResponse(
            Calisan calisan,
            List<Hizmet> hizmetler
    ) {
        this.calisanId = calisan.getId();
        this.calisanAdi = calisan.getCalisanAdi();
        this.calisanSoyad = calisan.getCalisanSoyad();
        this.calisanCinsiyet = calisan.getCalisanCinsiyet();
        this.calisanUnvan = calisan.getCalisanUnvan();
        this.calisanTelNo = calisan.getCalisanTelNo();
        this.calisanEmail = calisan.getCalisanEmail();
        this.isPasive = calisan.getPasive();
        this.hizmetler = hizmetler.stream().map(hizmet -> new HizmetPair(
                hizmet.getId(),
                hizmet.getHizmetAdi(),
                hizmet.getHizmetKategori().getId())).toList();
    }
}
