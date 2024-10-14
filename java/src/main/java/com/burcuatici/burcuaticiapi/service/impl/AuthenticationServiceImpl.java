package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Kullanici;
import com.burcuatici.burcuaticiapi.model.entity.request.SignUpRequest;
import com.burcuatici.burcuaticiapi.model.entity.request.SigninRequest;
import com.burcuatici.burcuaticiapi.model.entity.response.JwtAuthenticationResponse;
import com.burcuatici.burcuaticiapi.repository.KullaniciRepository;
import com.burcuatici.burcuaticiapi.security.Role;
import com.burcuatici.burcuaticiapi.service.AuthenticationService;
import com.burcuatici.burcuaticiapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

    private final KullaniciRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = Kullanici.builder()
                .kullaniciAdi(request.getKullaniciAdi())
                .kullaniciSoyadi(request.getKullaniciSoyadi())
                .kullaniciEmail(request.getKullaniciEmail())
                .kullaniciTelNo(request.getKullaniciTelNo())
                .kullaniciSifre(passwordEncoder.encode(request.getKullaniciSifre()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getKullaniciTelNo(), request.getKullaniciSifre()));
        var user = userRepository.findByKullaniciTelNo(request.getKullaniciTelNo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tel no or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}