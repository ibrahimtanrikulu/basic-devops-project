package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.Randevu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RandevuRepository {

    List<Randevu> findAll();

    List<Randevu> findAllByDateRandevu(LocalDateTime start, LocalDateTime end);

    Optional<Randevu> findById(Long id);

    Randevu save(Randevu randevu);

    void deleteById(Long id);
}
