package com.burcuatici.burcuaticiapi.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String kullaniciAdi;
    private String kullaniciSoyadi;
    private String kullaniciTelNo;
    private String kullaniciEmail;
    private String kullaniciSifre;
}
