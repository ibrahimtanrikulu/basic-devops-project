package com.burcuatici.burcuaticiapi.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {

    private String kullaniciTelNo;
    private String kullaniciSifre;
}