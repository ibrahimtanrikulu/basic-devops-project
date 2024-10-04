package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.Musteri;
import com.burcuatici.burcuaticiapi.service.MusteriService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musteri")
class MusteriController {

    private final MusteriService musteriService;

    public MusteriController(MusteriService musteriService) {
        this.musteriService = musteriService;
    }

    @GetMapping("/list")
    public List<Musteri> listMusteri() {
        return musteriService.findAllMusteri();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musteri> findById(
            @PathVariable Long id
    ) {
        Optional<Musteri> musteri = musteriService.findMusteriById(id);
        return musteri.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Musteri createMusteri(
            @RequestBody Musteri musteri
    ) {
        return musteriService.createMusteri(musteri);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Musteri> updateMusteri(
            @PathVariable Long id,
            @RequestBody Musteri yeniMusteri
    ) {
        try {
            Musteri updatedMusteri = musteriService.updateMusteri(id, yeniMusteri);
            return ResponseEntity.ok(updatedMusteri);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMusteri(
            @PathVariable Long id
    ) {
        musteriService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
