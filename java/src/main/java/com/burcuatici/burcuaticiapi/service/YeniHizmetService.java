package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateYeniHizmetRequest;

import java.util.List;
import java.util.Optional;


public interface YeniHizmetService {

    List<YeniHizmet> findAllYeniHizmet();

    Optional<YeniHizmet> findYeniHizmetById(Long id);

    YeniHizmet createYeniHizmet(CreateYeniHizmetRequest request);

    YeniHizmet updateYeniHizmet(Long id, YeniHizmet yeniHizmet);

    void deleteById(Long id);
}
