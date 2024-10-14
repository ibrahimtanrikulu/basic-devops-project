package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.CalisanHizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateCalisanHizmetRequest;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateCalisanRequest;
import com.burcuatici.burcuaticiapi.model.entity.response.CalisanResponse;
import com.burcuatici.burcuaticiapi.service.CalisanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calisan")
class CalisanController {

    private final CalisanService calisanService;

    public CalisanController(CalisanService calisanService) {
        this.calisanService = calisanService;
    }

    @GetMapping("/list")
    public List<CalisanResponse> findAll() {
        return calisanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalisanResponse> findCalisanById(
            @PathVariable Long id
    ) {
        Optional<CalisanResponse> calisan = calisanService.findCalisanById(id);
        return calisan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public CalisanResponse createCalisan(
            @RequestBody CreateCalisanRequest payload
    ) {
        return calisanService.createCalisan(payload);
    }

    @PostMapping("/{calisanId}")
    public ResponseEntity<CalisanHizmet> createCalisanHizmet(
            @PathVariable Long calisanId,
            @RequestBody CreateCalisanHizmetRequest createCalisanHizmetRequest
    ) {
        var calisanHizmet = calisanService.createCalisanHizmetRelation
                (createCalisanHizmetRequest.getHizmetIds(), calisanId, createCalisanHizmetRequest.getHizmetKategoriId());
        return new ResponseEntity<>(calisanHizmet, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CalisanResponse> updateCalisan(
            @PathVariable Long id,
            @RequestBody CreateCalisanRequest calisan
    ) {
        try {
            CalisanResponse updatedCalisan = calisanService.updateCalisan(id, calisan);
            return ResponseEntity.ok(updatedCalisan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCalisanById(
            @PathVariable Long id
    ) {
        calisanService.deleteCalisanById(id);
        return ResponseEntity.noContent().build();
    }
}
