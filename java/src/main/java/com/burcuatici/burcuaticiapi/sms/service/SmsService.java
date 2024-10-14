package com.burcuatici.burcuaticiapi.sms.service;

import com.burcuatici.burcuaticiapi.model.entity.Sms;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateSmsRequest;
import com.burcuatici.burcuaticiapi.sms.request.SmsRequest;

import java.util.List;

public interface SmsService {

    List<Sms> findAll();

    Sms create(CreateSmsRequest request);

    void deleteById(Long id);

    String sendSms(SmsRequest smsRequest);
}
