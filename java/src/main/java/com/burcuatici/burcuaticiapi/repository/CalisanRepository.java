package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.Calisan;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateCalisanRequest;

import java.util.List;
import java.util.Optional;

public interface CalisanRepository {

    List<Calisan> findAll();

    Optional<Calisan> findById(Long id);

    Calisan save(Calisan calisan);

    Calisan update(Long id, CreateCalisanRequest calisan);

    void deleteById(Long id);
}
