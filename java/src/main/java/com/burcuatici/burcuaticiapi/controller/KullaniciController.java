package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.Kullanici;
import com.burcuatici.burcuaticiapi.model.entity.request.KullaniciRequest;
import com.burcuatici.burcuaticiapi.model.entity.request.SignUpRequest;
import com.burcuatici.burcuaticiapi.model.entity.request.SigninRequest;
import com.burcuatici.burcuaticiapi.model.entity.response.JwtAuthenticationResponse;
import com.burcuatici.burcuaticiapi.service.AuthenticationService;
import com.burcuatici.burcuaticiapi.service.KullaniciService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kullanici")
class KullaniciController {

    private final AuthenticationService authenticationService;
    private final KullaniciService kullaniciService;

    KullaniciController(AuthenticationService authenticationService, KullaniciService kullaniciService) {
        this.authenticationService = authenticationService;
        this.kullaniciService = kullaniciService;
    }

    @PostMapping("/create")
    public ResponseEntity<JwtAuthenticationResponse> signup(
            @RequestBody SignUpRequest request
    ) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(
            @RequestBody SigninRequest request
    ) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @GetMapping("/list")
    public List<Kullanici> findAll() {
        return kullaniciService.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Kullanici> updateKullanici(
            @PathVariable Long id,
            @RequestBody KullaniciRequest request
    ) {
        try {
            Kullanici updatedKullanici = kullaniciService.update(id, request);
            return ResponseEntity.ok(updatedKullanici);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}