package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.CalisanHizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateCalisanRequest;
import com.burcuatici.burcuaticiapi.model.entity.response.CalisanResponse;

import java.util.List;
import java.util.Optional;

public interface CalisanService {

    List<CalisanResponse> findAll();

    Optional<CalisanResponse> findCalisanById(Long id);

    CalisanResponse createCalisan(CreateCalisanRequest payload);

    CalisanHizmet createCalisanHizmetRelation(List<Long> hizmetIds, Long calisanId, Long hizmetKategoriId);

    CalisanResponse updateCalisan(Long id, CreateCalisanRequest calisan);

    void deleteCalisanById(Long id);
}