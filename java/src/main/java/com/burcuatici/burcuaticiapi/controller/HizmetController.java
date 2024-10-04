package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.HizmetUpdateRequest;
import com.burcuatici.burcuaticiapi.service.HizmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hizmet")
class HizmetController {

    private final HizmetService hizmetService;

    public HizmetController(HizmetService hizmetService) {
        this.hizmetService = hizmetService;
    }

    @GetMapping("/list")
    public List<Hizmet> findAllHizmet() {
        return hizmetService.findAllHizmet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hizmet> findHizmet(
            @PathVariable Long id
    ) {
        Optional<Hizmet> hizmet = hizmetService.findHizmetById(id);
        return hizmet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create/{kategoriId}")
    public Hizmet createHizmet(
            @PathVariable Long kategoriId,
            @RequestBody String hizmetAdi
    ) {
        return hizmetService.createHizmet(kategoriId, hizmetAdi);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hizmet> updateHizmet(
            @PathVariable Long id,
            @RequestBody HizmetUpdateRequest request
    ) {
        try {
            Hizmet updatedHizmet = hizmetService.updateHizmet(id, request.getHizmetAdi());
            return ResponseEntity.ok(updatedHizmet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHizmet(
            @PathVariable Long id
    ) {
        hizmetService.deleteHizmetById(id);
        return ResponseEntity.noContent().build();
    }
}
