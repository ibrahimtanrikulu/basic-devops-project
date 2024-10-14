package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Calisan;
import com.burcuatici.burcuaticiapi.model.entity.CalisanHizmet;
import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateCalisanRequest;
import com.burcuatici.burcuaticiapi.model.entity.response.CalisanResponse;
import com.burcuatici.burcuaticiapi.repository.CalisanRepository;
import com.burcuatici.burcuaticiapi.repository.HizmetKategoriRepository;
import com.burcuatici.burcuaticiapi.repository.HizmetRepository;
import com.burcuatici.burcuaticiapi.service.CalisanService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class CalisanServiceImpl implements CalisanService {

    private final CalisanRepository calisanRepository;
    private final HizmetRepository hizmetRepository;
    private final HizmetKategoriRepository hizmetKategoriRepository;

    public CalisanServiceImpl(CalisanRepository calisanRepository, HizmetRepository hizmetRepository, HizmetKategoriRepository hizmetKategoriRepository) {
        this.calisanRepository = calisanRepository;
        this.hizmetRepository = hizmetRepository;
        this.hizmetKategoriRepository = hizmetKategoriRepository;
    }

    @Override
    public List<CalisanResponse> findAll() {
        var calisanList = calisanRepository.findAll();
        return calisanList.stream().map(it -> new CalisanResponse(it, it.getHizmetler().stream().toList())).toList();
    }

    @Override
    public Optional<CalisanResponse> findCalisanById(Long id) {
        var calisan = calisanRepository.findById(id);
        return calisan.map(it -> new CalisanResponse(it, it.getHizmetler().stream().toList()));
    }

    @Override
    @Transactional
    public CalisanResponse createCalisan(CreateCalisanRequest payload) {
        var calisanEntity = calisanRepository.save(payload.toCalisan());
        List<Hizmet> hizmetler = hizmetRepository.findAllById(payload.getHizmetIds());

        var updatedHizmetler = calisanEntity.getHizmetler();
        updatedHizmetler.addAll(hizmetler);
        calisanEntity.setHizmetler(updatedHizmetler);
        var updatedCalisan = calisanRepository.save(calisanEntity);
        return new CalisanResponse(updatedCalisan, updatedCalisan.getHizmetler().stream().toList());
    }

    @Override
    @Transactional
    public CalisanHizmet createCalisanHizmetRelation(List<Long> hizmetIds, Long calisanId, Long hizmetKategoriId) {
        Calisan calisan = calisanRepository.findById(calisanId).orElseThrow(()
                -> new EntityNotFoundException("Calisan bulunamadı"));

        List<Hizmet> hizmetler = hizmetRepository.findAllById(hizmetIds);

        HizmetKategori hizmetKategori = hizmetKategoriRepository.findById(hizmetKategoriId)
                .orElseThrow(() -> new EntityNotFoundException("Hizmet Kategori bulunamadı"));

        var updatedHizmetler = calisan.getHizmetler();
        updatedHizmetler.addAll(hizmetler);
        calisan.setHizmetler(updatedHizmetler);
        calisanRepository.save(calisan);

        return new CalisanHizmet(calisan.getId(), hizmetler, hizmetKategori);
    }

    @Override
    public CalisanResponse updateCalisan(Long id, CreateCalisanRequest calisan) {
        Optional<Calisan> eskiCalisan = calisanRepository.findById(id);
        if (eskiCalisan.isPresent()) {
            var updatedCalisan = calisanRepository.update(id, calisan);
            var hizmetlerOfCalisan = updatedCalisan.getHizmetler();
            return new CalisanResponse(updatedCalisan, hizmetlerOfCalisan.stream().toList());
        } else {
            throw new IllegalArgumentException("Calisan bulunamadı: " + id);
        }
    }

    @Override
    public void deleteCalisanById(Long id) {
        calisanRepository.deleteById(id);
    }
}



