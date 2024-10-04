package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;

import java.util.List;
import java.util.Optional;

public interface HizmetService {

    List<Hizmet> findAllHizmet();

    Optional<Hizmet> findHizmetById(Long id);

    Hizmet createHizmet(Long kategoriId, String hizmetAdi);

    Hizmet updateHizmet(Long id, String hizmetAdi);

    void deleteHizmetById(Long id);
}
