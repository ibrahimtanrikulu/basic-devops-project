package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;
import com.burcuatici.burcuaticiapi.repository.HizmetKategoriRepository;
import com.burcuatici.burcuaticiapi.service.HizmetKategoriService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class HizmetKategoriServiceImpl implements HizmetKategoriService {

    private final HizmetKategoriRepository hizmetKategoriRepository;

    public HizmetKategoriServiceImpl(HizmetKategoriRepository hizmetKategoriRepository) {
        this.hizmetKategoriRepository = hizmetKategoriRepository;
    }

    @Override
    public List<HizmetKategori> findAll() {
        return hizmetKategoriRepository.findAll();
    }

    @Override
    public Optional<HizmetKategori> findHizmetKategoriById(Long id) {
        return hizmetKategoriRepository.findById(id);
    }

    @Override
    public List<HizmetKategori> findAllWithHizmetler() {
        return hizmetKategoriRepository.findAllWithHizmetler();
    }

    @Override
    public HizmetKategori createHizmetKategori(HizmetKategori hizmetKategori) {
        return hizmetKategoriRepository.save(saveHizmetKategori(hizmetKategori));
    }

    private HizmetKategori saveHizmetKategori(HizmetKategori yeniHizmetKategori) {
        return new HizmetKategori(
                yeniHizmetKategori.getHizmetKategoriAdi()
        );
    }

    @Override
    public HizmetKategori updateHizmetKategori(Long id, HizmetKategori guncelHizmetKategori) {
        Optional<HizmetKategori> eskiHizmetKategori = hizmetKategoriRepository.findById(id);
        if (eskiHizmetKategori.isPresent()) {
            return hizmetKategoriRepository.update(id, guncelHizmetKategori);
        } else {
            throw new IllegalArgumentException("HizmetKategori bulunamadı: " + id);
        }
    }

    @Override
    public void deleteHizmetKategoriById(Long id) {
        hizmetKategoriRepository.deleteById(id);
    }
}
