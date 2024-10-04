package com.burcuatici.burcuaticiapi.model.entity.request;

import com.burcuatici.burcuaticiapi.model.entity.Sms;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateSmsRequest {

    private String smsAdi;
    private String aciklama;
    private LocalDateTime tarih;
    private Boolean isPasive;

    public CreateSmsRequest(String smsAdi, String aciklama, LocalDateTime tarih, Boolean isPasive) {
        this.smsAdi = smsAdi;
        this.aciklama = aciklama;
        this.tarih = tarih;
        this.isPasive = isPasive;
    }

    public Sms toSms() {
        return new Sms(
                smsAdi,
                aciklama,
                tarih,
                isPasive
        );
    }


}
