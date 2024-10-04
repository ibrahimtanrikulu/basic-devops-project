package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.Musteri;

import java.util.List;
import java.util.Optional;


public interface MusteriRepository {

    List<Musteri> findAll();

    Optional<Musteri> findById(Long id);

    Musteri save(Musteri musteri);

    Musteri update(Long id, Musteri musteri);

    void deleteById(Long id);
}
