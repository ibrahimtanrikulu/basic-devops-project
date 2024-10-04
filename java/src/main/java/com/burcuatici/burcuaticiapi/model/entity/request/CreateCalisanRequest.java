package com.burcuatici.burcuaticiapi.model.entity.request;

import com.burcuatici.burcuaticiapi.model.entity.Calisan;

import java.util.List;

public class CreateCalisanRequest {

    private final String calisanAdi;
    private final String calisanSoyad;
    private final String calisanCinsiyet;
    private final String calisanUnvan;
    private final String calisanTelNo;
    private final String calisanEmail;
    private boolean isPasive;
    private final List<Long> hizmetIds;

    public CreateCalisanRequest(
            String calisanAdi,
            String calisanSoyad,
            String calisanCinsiyet,
            String calisanUnvan,
            String calisanTelNo,
            String calisanEmail,
            boolean isPasive,
            List<Long> hizmetIds
    ) {
        this.calisanAdi = calisanAdi;
        this.calisanSoyad = calisanSoyad;
        this.calisanCinsiyet = calisanCinsiyet;
        this.calisanUnvan = calisanUnvan;
        this.calisanTelNo = calisanTelNo;
        this.calisanEmail = calisanEmail;
        this.isPasive = isPasive;
        this.hizmetIds = hizmetIds;
    }

    public Calisan toCalisan() {
        return new Calisan(
                calisanAdi,
                calisanSoyad,
                calisanCinsiyet,
                calisanUnvan,
                calisanTelNo,
                calisanEmail,
                isPasive
        );
    }

    public boolean isPasive() {
        return isPasive;
    }

    public void setPasive(boolean pasive) {
        isPasive = pasive;
    }

    public List<Long> getHizmetIds() {
        return hizmetIds;
    }
}
