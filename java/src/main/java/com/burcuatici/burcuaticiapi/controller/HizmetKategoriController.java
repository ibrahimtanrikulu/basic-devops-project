package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;
import com.burcuatici.burcuaticiapi.service.HizmetKategoriService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hizmetKategori")
class HizmetKategoriController {

    private final HizmetKategoriService hizmetKategoriService;

    public HizmetKategoriController(HizmetKategoriService hizmetKategoriService) {
        this.hizmetKategoriService = hizmetKategoriService;
    }

    @GetMapping("/list")
    public List<HizmetKategori> findAllWithHizmetler() {
        return hizmetKategoriService.findAllWithHizmetler();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HizmetKategori> findHizmetKategoriById(
            @PathVariable Long id
    ) {
        Optional<HizmetKategori> hizmetKategori = hizmetKategoriService.findHizmetKategoriById(id);
        return hizmetKategori.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public HizmetKategori createHizmetKategori(
            @RequestBody HizmetKategori hizmetKategori
    ) {
        return hizmetKategoriService.createHizmetKategori(hizmetKategori);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HizmetKategori> updateHizmetKategori(
            @PathVariable Long id,
            @RequestBody HizmetKategori hizmetKategori
    ) {
        try {
            HizmetKategori updatedHizmetKategori = hizmetKategoriService.updateHizmetKategori(id, hizmetKategori);
            return ResponseEntity.ok(updatedHizmetKategori);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHizmetKategoriById(
            @PathVariable Long id
    ) {
        hizmetKategoriService.deleteHizmetKategoriById(id);
        return ResponseEntity.noContent().build();
    }
}
