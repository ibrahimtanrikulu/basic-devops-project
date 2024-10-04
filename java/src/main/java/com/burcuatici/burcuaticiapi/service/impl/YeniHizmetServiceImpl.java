package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;
import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateYeniHizmetRequest;
import com.burcuatici.burcuaticiapi.repository.HizmetKategoriRepository;
import com.burcuatici.burcuaticiapi.repository.HizmetRepository;
import com.burcuatici.burcuaticiapi.repository.YeniHizmetRepository;
import com.burcuatici.burcuaticiapi.service.YeniHizmetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class YeniHizmetServiceImpl implements YeniHizmetService {

    private final YeniHizmetRepository yeniHizmetRepository;
    private final HizmetKategoriRepository hizmetKategoriRepository;
    private final HizmetRepository hizmetRepository;

    public YeniHizmetServiceImpl(
            YeniHizmetRepository yeniHizmetRepository,
            HizmetKategoriRepository hizmetKategoriRepository,
            HizmetRepository hizmetRepository
    ) {
        this.yeniHizmetRepository = yeniHizmetRepository;
        this.hizmetKategoriRepository = hizmetKategoriRepository;
        this.hizmetRepository = hizmetRepository;

    }

    @Override
    public List<YeniHizmet> findAllYeniHizmet() {
        return yeniHizmetRepository.findAll();
    }

    @Override
    public Optional<YeniHizmet> findYeniHizmetById(Long id) {
        return yeniHizmetRepository.findById(id);
    }

    @Override
    public YeniHizmet createYeniHizmet(CreateYeniHizmetRequest request) {
        HizmetKategori hizmetKategori = hizmetKategoriRepository.findById(request.getHizmetKategoriID())
                .orElseThrow(() -> new IllegalArgumentException("Hizmet kategori bulunamadı"));

        Hizmet hizmet = hizmetRepository.findById(request.getHizmetID())
                .orElseThrow(() -> new IllegalArgumentException("Hizmet bulunamadı"));

        YeniHizmet yeniHizmet = request.toYeniHizmet();
        yeniHizmet.setHizmetKategoriID(hizmetKategori.getId());
        yeniHizmet.setHizmetID(hizmet.getId());

        return yeniHizmetRepository.save(yeniHizmet);
    }

    @Override
    public YeniHizmet updateYeniHizmet(Long id, YeniHizmet yeniHizmet) {
        Optional<YeniHizmet> eskiYeniHizmet = yeniHizmetRepository.findById(id);
        if (eskiYeniHizmet.isPresent()) {
            return yeniHizmetRepository.update(id, yeniHizmet);
        } else {
            throw new IllegalArgumentException("Yeni hizmet bulunamadı: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        yeniHizmetRepository.deleteById(id);
    }
}
