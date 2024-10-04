package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateYeniHizmetRequest;
import com.burcuatici.burcuaticiapi.service.YeniHizmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/yenihizmet")
class YeniHizmetController {

    private final YeniHizmetService yeniHizmetService;

    public YeniHizmetController(YeniHizmetService yeniHizmetService) {
        this.yeniHizmetService = yeniHizmetService;
    }

    @GetMapping("/list")
    public List<YeniHizmet> listYeniHizmet() {
        return yeniHizmetService.findAllYeniHizmet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<YeniHizmet> findById(
            @PathVariable Long id
    ) {
        Optional<YeniHizmet> yeniHizmet = yeniHizmetService.findYeniHizmetById(id);
        return yeniHizmet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public YeniHizmet createYeniHizmet(
            @RequestBody CreateYeniHizmetRequest createYeniHizmetRequest) {
        return yeniHizmetService.createYeniHizmet(createYeniHizmetRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<YeniHizmet> updateYeniHizmet(
            @PathVariable Long id,
            @RequestBody YeniHizmet yeniHizmet) {
        try {
            YeniHizmet updatedYenHizmet = yeniHizmetService.updateYeniHizmet(id, yeniHizmet);
            return ResponseEntity.ok(updatedYenHizmet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteYeniHizmet(
            @PathVariable Long id
    ) {
        yeniHizmetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
