package com.burcuatici.burcuaticiapi.model.entity.request;

public class KullaniciRequest {

    private String kullaniciAdi;
    private String kullaniciSoyadi;
    private String kullaniciTelNo;
    private String kullaniciEmail;


    public KullaniciRequest(
            String kullaniciAdi,
            String kullaniciSoyadi,
            String kullaniciTelNo,
            String kullaniciEmail
    ) {
        this.kullaniciAdi = kullaniciAdi;
        this.kullaniciSoyadi = kullaniciSoyadi;
        this.kullaniciTelNo = kullaniciTelNo;
        this.kullaniciEmail = kullaniciEmail;

    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getKullaniciSoyadi() {
        return kullaniciSoyadi;
    }

    public String getKullaniciTelNo() {
        return kullaniciTelNo;
    }

    public String getKullaniciEmail() {
        return kullaniciEmail;
    }
}
