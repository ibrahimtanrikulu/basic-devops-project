package com.burcuatici.burcuaticiapi.sms;

import com.burcuatici.burcuaticiapi.model.entity.Sms;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateSmsRequest;
import com.burcuatici.burcuaticiapi.sms.request.SmsRequest;
import com.burcuatici.burcuaticiapi.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms")
class SmsController {

    @Value("${sms.api.url}")
    private String smsApiUrl;

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/list")
    public List<Sms> smsList() {
        return smsService.findAll();
    }

    @PostMapping("/create")
    public Sms createSms(
            @RequestBody CreateSmsRequest request
    ) {
        return smsService.create(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        smsService.deleteById(id);
    }

    @PostMapping("/send-sms")
    public String sendSms(@RequestBody SmsRequest smsRequest) {
        return smsService.sendSms(smsRequest);
    }

}
