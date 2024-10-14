package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;

import java.util.List;
import java.util.Optional;

public interface HizmetRepository {

    Optional<Hizmet> findById(Long id);

    List<Hizmet> findAll();

    List<Hizmet> findAllById(List<Long> hizmetIds);

    List<Hizmet> findAllByHizmetKategoriId(Long hizmetKategoriId);

    Hizmet save(Hizmet yeniHizmet);

    Hizmet update(Long id, Hizmet guncelHizmet);

    void deleteById(Long id);
}
