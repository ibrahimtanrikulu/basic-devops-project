package com.burcuatici.burcuaticiapi.sms.service.impl;

import com.burcuatici.burcuaticiapi.sms.request.SmsRequest;
import com.burcuatici.burcuaticiapi.sms.service.SmsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class SmsScheduledService {

    private final SmsService smsService;

    public SmsScheduledService(SmsService smsService) {
        this.smsService = smsService;
    }

    @Scheduled(cron = "0 0 12 * * ?") // Every day at 12:00 PM
    public void sendReminderSms() {
        try {
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();

            // Calculate date one day before randevuTarih
            LocalDateTime oneDayBeforeRandevuTarih = now.minusDays(1);

            // Convert the date to a string using a specific format
            String oneDayBeforeRandevuTarihString = oneDayBeforeRandevuTarih.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Create an SmsRequest object with the calculated date
            SmsRequest smsRequest = new SmsRequest();
            smsRequest.setMessageType("text"); // Message type (e.g., text)
            smsRequest.setMessage("Your appointment is tomorrow."); // SMS content
            smsRequest.setMessageContentType("plain"); // Message content type (e.g., plain text)
            smsRequest.setPhones(Arrays.asList("PHONE_NUMBER_1", "PHONE_NUMBER_2")); // Phone numbers to send SMS
            smsRequest.setRandevuTarih(LocalDateTime.parse(oneDayBeforeRandevuTarihString)); // Randevu tarihi

            // Call the sendSms method of SmsService
            String result = smsService.sendSms(smsRequest);

            // Log the result or handle it as needed
            System.out.println("SMS send result: " + result);

        } catch (Exception e) {
            // Log the error or handle it as needed
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
