package com.burcuatici.burcuaticiapi.repository;

import com.burcuatici.burcuaticiapi.model.entity.Sms;

import java.util.List;

public interface SmsRepository {

    List<Sms> findAll();

    Sms save(Sms sms);

    void deleteById(Long id);
}
