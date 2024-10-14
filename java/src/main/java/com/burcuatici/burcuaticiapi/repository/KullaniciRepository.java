package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.Kullanici;
import com.burcuatici.burcuaticiapi.model.entity.request.KullaniciRequest;

import java.util.List;
import java.util.Optional;

public interface KullaniciRepository {

    List<Kullanici> findAll();

    Optional<Kullanici> findById(Long id);

    Optional<Kullanici> findByKullaniciTelNo(String kullaniciTelNo);

    Kullanici save(Kullanici kullanici);

    Kullanici update(Long id, KullaniciRequest kullanici);

}
