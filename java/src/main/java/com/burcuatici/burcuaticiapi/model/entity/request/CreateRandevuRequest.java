package com.burcuatici.burcuaticiapi.model.entity.request;

import com.burcuatici.burcuaticiapi.model.entity.Randevu;
import com.burcuatici.burcuaticiapi.repository.RandevuRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateRandevuRequest {

    private Long hizmet;
    private Long calisan;
    private Long musteri;
    private LocalDateTime randevuTarih;
    private String randevuAciklama;
    private Boolean isPasive;

    public CreateRandevuRequest(Long hizmet, Long calisan, Long musteri, LocalDateTime randevuTarih,String randevuAciklama, Boolean isPasive) {
        this.hizmet = hizmet;
        this.calisan = calisan;
        this.musteri = musteri;
        this.randevuTarih = randevuTarih;
        this.randevuAciklama = randevuAciklama;
        this.isPasive = isPasive;
    }

    public Randevu toRandevu() {
        return new Randevu(
                hizmet,
                calisan,
                musteri,
                randevuTarih,
                randevuAciklama,
                isPasive
        );
    }
}
