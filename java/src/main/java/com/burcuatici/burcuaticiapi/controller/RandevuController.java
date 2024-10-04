package com.burcuatici.burcuaticiapi.controller;

import com.burcuatici.burcuaticiapi.model.entity.Randevu;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateRandevuRequest;
import com.burcuatici.burcuaticiapi.service.RandevuService;
import com.burcuatici.burcuaticiapi.sms.request.SmsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/randevu")
class RandevuController {

    private final RandevuService randevuService;

    public RandevuController(RandevuService randevuService) {
        this.randevuService = randevuService;
    }

    @GetMapping("/list")
    public List<Randevu> listRandevu() {
        return randevuService.findAll();
    }

    @GetMapping("/list/by-date/{selectedDate}")
    public List<Randevu> listByDateRandevu(
            @PathVariable("selectedDate") String selectedDate
    ) {
        LocalDate parsedDate = LocalDate.parse(
                selectedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        LocalDateTime startOfDay = parsedDate.atStartOfDay();
        LocalDateTime endOfDay = parsedDate.atTime(LocalTime.MAX);

        LocalDateTime oneMonthBefore = startOfDay.minusMonths(1);
        LocalDateTime oneMonthAfter = endOfDay.plusMonths(1);

        return randevuService.findAllByDateRandevu(oneMonthBefore, oneMonthAfter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Randevu> findRandevu(
            @PathVariable Long id
    ) {
        Optional<Randevu> randevu = randevuService.findRandevuById(id);
        return randevu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Randevu createRandevu(
            @RequestBody CreateRandevuRequest request) {
        return randevuService.createRandevu(request);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRandevu(
            @PathVariable Long id
    ) {
        randevuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
/*
    @PostMapping("/send")
    public void sendSms(){
        randevuService.sendSmsRemindersOneDayBefore();
    }*/
}

