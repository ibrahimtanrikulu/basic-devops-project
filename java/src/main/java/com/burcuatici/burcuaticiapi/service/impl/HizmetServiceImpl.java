package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.repository.HizmetKategoriRepository;
import com.burcuatici.burcuaticiapi.repository.HizmetRepository;
import com.burcuatici.burcuaticiapi.service.HizmetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class HizmetServiceImpl implements HizmetService {

    private final HizmetRepository hizmetRepository;
    private final HizmetKategoriRepository hizmetKategoriRepository;

    public HizmetServiceImpl(HizmetRepository hizmetRepository, HizmetKategoriRepository hizmetKategoriRepository) {
        this.hizmetRepository = hizmetRepository;
        this.hizmetKategoriRepository = hizmetKategoriRepository;
    }

    @Override
    public List<Hizmet> findAllHizmet() {
        return hizmetRepository.findAll();
    }

    @Override
    public Optional<Hizmet> findHizmetById(Long id) {
        return hizmetRepository.findById(id);
    }

    @Override
    public Hizmet createHizmet(Long kategoriId, String hizmetAdi) {
        var hizmetKategori = hizmetKategoriRepository.findById(kategoriId)
                .orElseThrow(() -> new EntityNotFoundException("HizmetKategori not found with ID: " + kategoriId));

        var yeniHizmet = new Hizmet(
                hizmetKategori,
                hizmetAdi
        );
        return hizmetRepository.save(yeniHizmet);
    }

    @Override
    public Hizmet updateHizmet(Long id, String hizmetAdi) {
        Optional<Hizmet> eskiHizmet = hizmetRepository.findById(id);
        if (eskiHizmet.isPresent()) {
            Hizmet hizmetToUpdate = eskiHizmet.get();
            hizmetToUpdate.setHizmetAdi(hizmetAdi);
            return hizmetRepository.update(id, hizmetToUpdate);
        } else {
            throw new IllegalArgumentException("Hizmet bulunamadı: " + id);
        }
    }

    @Override
    public void deleteHizmetById(Long id) {
        hizmetRepository.deleteById(id);
    }
}
