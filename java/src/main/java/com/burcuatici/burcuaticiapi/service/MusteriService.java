package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.Musteri;

import java.util.List;
import java.util.Optional;

public interface MusteriService {

    List<Musteri> findAllMusteri();

    Optional<Musteri> findMusteriById(Long id);

    Musteri createMusteri(Musteri musteri);

    Musteri updateMusteri(Long id, Musteri musteri);

    void deleteById(Long id);
}
