package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Musteri;
import com.burcuatici.burcuaticiapi.repository.MusteriRepository;
import com.burcuatici.burcuaticiapi.service.MusteriService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class MusteriServiceImpl implements MusteriService {

    private final MusteriRepository musteriRepository;

    public MusteriServiceImpl(MusteriRepository musteriRepository) {
        this.musteriRepository = musteriRepository;
    }

    @Override
    public List<Musteri> findAllMusteri() {
        return musteriRepository.findAll();
    }

    @Override
    public Optional<Musteri> findMusteriById(Long id) {
        return musteriRepository.findById(id);
    }

    @Override
    public Musteri createMusteri(Musteri musteri) {
        return musteriRepository.save(createMusteriEntity(musteri));
    }

    private Musteri createMusteriEntity(Musteri musteri) {
        return new Musteri(
                musteri.getMusteriAdi(),
                musteri.getMusteriSoyadi(),
                musteri.getMusteriCinsiyet(),
                musteri.getMusteriTelNo(),
                musteri.getMusteriTelNo2(),
                musteri.getMusteriEmail(),
                musteri.getMusteriDogumGunu(),
                musteri.isMusteriKaraListe()
        );
    }

    @Override
    public Musteri updateMusteri(Long id, Musteri yeniMusteri) {
        Optional<Musteri> eskiMusteri = musteriRepository.findById(id);
        if (eskiMusteri.isPresent()) {
            return musteriRepository.update(id, yeniMusteri);
        } else {
            throw new IllegalArgumentException("Müşteri bulunamadı: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        musteriRepository.deleteById(id);
    }
}
