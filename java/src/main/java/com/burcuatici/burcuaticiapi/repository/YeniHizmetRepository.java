package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;

import java.util.List;
import java.util.Optional;

public interface YeniHizmetRepository {

    List<YeniHizmet> findAll();

    Optional<YeniHizmet> findById(Long id);

    YeniHizmet save(YeniHizmet yeniHizmet);

    YeniHizmet update(Long id, YeniHizmet yeniHizmet);

    void deleteById(Long id);

}
