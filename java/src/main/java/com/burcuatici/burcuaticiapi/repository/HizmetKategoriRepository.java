package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;

import java.util.List;
import java.util.Optional;

public interface HizmetKategoriRepository {

    Optional<HizmetKategori> findById(Long id);

    List<HizmetKategori> findAll();

    List<HizmetKategori> findAllWithHizmetler();

    HizmetKategori save(HizmetKategori yeniHizmetKategori);

    HizmetKategori update(Long id, HizmetKategori guncelHizmet);

    void deleteById(Long id);
}
