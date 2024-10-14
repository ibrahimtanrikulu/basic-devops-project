package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.Kullanici;
import com.burcuatici.burcuaticiapi.model.entity.request.KullaniciRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface KullaniciService {

    List<Kullanici> findAll();

    Kullanici update(Long id, KullaniciRequest request);

    UserDetailsService userDetailsService();
}
