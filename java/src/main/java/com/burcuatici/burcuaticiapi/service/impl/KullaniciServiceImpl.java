package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Kullanici;
import com.burcuatici.burcuaticiapi.model.entity.request.KullaniciRequest;
import com.burcuatici.burcuaticiapi.repository.KullaniciRepository;
import com.burcuatici.burcuaticiapi.service.KullaniciService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;

    public KullaniciServiceImpl(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    @Override
    public List<Kullanici> findAll() {
        return kullaniciRepository.findAll();
    }

    @Override
    public Kullanici update(Long id, KullaniciRequest kullaniciRequest) {
        Optional<Kullanici> eskiKullanici = kullaniciRepository.findById(id);
        if (eskiKullanici.isPresent()) {
            return kullaniciRepository.update(id, kullaniciRequest);
        } else {
            throw new IllegalArgumentException("Kullanici bulunamadı: " + id);
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> kullaniciRepository.findByKullaniciTelNo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
