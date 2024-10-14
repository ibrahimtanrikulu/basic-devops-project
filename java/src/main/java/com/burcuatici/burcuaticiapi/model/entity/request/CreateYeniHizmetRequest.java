package com.burcuatici.burcuaticiapi.model.entity.request;

import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateYeniHizmetRequest {

    private Long hizmetKategoriID;
    private Long hizmetID;
    private String yeniHizmetAdi;
    private String aciklama;
    private Integer ortalamaIslemSuresi;
    private Boolean isPasive;
    private String calisanIds;

    public CreateYeniHizmetRequest(
            Long hizmetKategoriID,
            Long hizmetID,
            String yeniHizmetAdi,
            String aciklama,
            Integer ortalamaIslemSuresi,
            Boolean isPasive,
            String calisanIds
    ) {
        this.hizmetKategoriID = hizmetKategoriID;
        this.hizmetID = hizmetID;
        this.yeniHizmetAdi = yeniHizmetAdi;
        this.aciklama = aciklama;
        this.ortalamaIslemSuresi = ortalamaIslemSuresi;
        this.isPasive = isPasive;
        this.calisanIds = calisanIds;
    }

    public YeniHizmet toYeniHizmet() {
        return new YeniHizmet(
                hizmetKategoriID,
                hizmetID,
                yeniHizmetAdi,
                aciklama,
                ortalamaIslemSuresi,
                isPasive,
                calisanIds
        );
    }
}
