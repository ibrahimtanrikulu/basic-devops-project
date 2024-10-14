package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;

import java.util.List;
import java.util.Optional;

public interface HizmetKategoriService {

    List<HizmetKategori> findAll();

    Optional<HizmetKategori> findHizmetKategoriById(Long id);

    List<HizmetKategori> findAllWithHizmetler();

    HizmetKategori createHizmetKategori(HizmetKategori hizmetKategori);

    HizmetKategori updateHizmetKategori(Long id, HizmetKategori guncelHizmetKategori);

    void deleteHizmetKategoriById(Long id);
}
