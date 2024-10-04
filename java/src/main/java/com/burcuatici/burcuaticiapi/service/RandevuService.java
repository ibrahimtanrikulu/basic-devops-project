package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.Randevu;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateRandevuRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RandevuService {

    List<Randevu> findAll();

    List<Randevu> findAllByDateRandevu(LocalDateTime start, LocalDateTime end);

    Optional<Randevu> findRandevuById(Long id);

    Randevu createRandevu(CreateRandevuRequest request);

    void deleteById(Long id);

    void sendSmsRemindersOneDayBefore();

    void sendSmsRemindersThreeHoursBefore();

}
