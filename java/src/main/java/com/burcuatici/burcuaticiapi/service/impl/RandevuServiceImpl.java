package com.burcuatici.burcuaticiapi.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Musteri;
import com.burcuatici.burcuaticiapi.model.entity.Randevu;
import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateRandevuRequest;
import com.burcuatici.burcuaticiapi.repository.MusteriRepository;
import com.burcuatici.burcuaticiapi.repository.RandevuRepository;
import com.burcuatici.burcuaticiapi.repository.YeniHizmetRepository;
import com.burcuatici.burcuaticiapi.service.RandevuService;
import com.burcuatici.burcuaticiapi.sms.request.SmsRequest;
import com.burcuatici.burcuaticiapi.sms.service.SmsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
class RandevuServiceImpl implements RandevuService {

    private final RandevuRepository randevuRepository;
    private final SmsService smsService;
    private final YeniHizmetRepository yeniHizmetRepository;
    private final MusteriRepository musteriRepository;

    public RandevuServiceImpl(
            RandevuRepository randevuRepository,
            SmsService smsService, YeniHizmetRepository yeniHizmetRepository, MusteriRepository musteriRepository) {
        this.randevuRepository = randevuRepository;
        this.smsService = smsService;
        this.yeniHizmetRepository = yeniHizmetRepository;
        this.musteriRepository = musteriRepository;
    }

    @Override
    public List<Randevu> findAll() {
        return randevuRepository.findAll();
    }

    @Override
    public List<Randevu> findAllByDateRandevu(LocalDateTime start, LocalDateTime end) {
        return randevuRepository.findAllByDateRandevu(start, end);
    }

    @Override
    public Optional<Randevu> findRandevuById(Long id) {
        return randevuRepository.findById(id);
    }

    @Override
    public Randevu createRandevu(CreateRandevuRequest request) {
        Randevu randevu = request.toRandevu();
        Randevu savedRandevu = randevuRepository.save(randevu);

        // Retrieve the date from the saved randevu object.
        LocalDateTime randevuTarih = savedRandevu.getRandevuTarih();

        // Send SMS reminders using the retrieved date.
        sendSmsReminderOneDayBefore(randevuTarih);
        sendSmsReminderThreeHoursBefore(randevuTarih);

        return savedRandevu;
    }

    @Override
    public void deleteById(Long id) {
        randevuRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendSmsRemindersOneDayBefore() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusDays(1);

        List<Randevu> randevular = findAllByDateRandevu(now, end);

        for (Randevu randevu : randevular) {
            System.out.println("1 gün önceki SMS hatırlatıcısı gönderiliyor: " + randevu.getId());
            sendSmsReminderOneDayBefore(randevu.getRandevuTarih());
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    public void sendSmsRemindersThreeHoursBefore() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeHoursBefore = now.minusHours(3);

        List<Randevu> randevular = findAllByDateRandevu(now, threeHoursBefore);

        for (Randevu randevu : randevular) {
            System.out.println("3 saat önceki SMS hatırlatıcısı gönderiliyor: " + randevu.getId());
            sendSmsReminderThreeHoursBefore(randevu.getRandevuTarih());
        }
    }


    private void sendSmsReminderOneDayBefore(LocalDateTime randevuTarih) {
        LocalDateTime oneDayBeforeRandevuTarih = randevuTarih.minusDays(1);

        List<Randevu> randevular = findAllByDateRandevu(oneDayBeforeRandevuTarih, oneDayBeforeRandevuTarih.plusDays(1));

        for (Randevu randevu : randevular) {
            System.out.println("1gün önceki SMS hatırlatıcısı gönderiliyor: " + randevu.getId());

            sendReminderSms(randevu);
        }
    }

    private void sendSmsReminderThreeHoursBefore(LocalDateTime randevuTarih) {
        LocalDateTime threeHoursBeforeRandevuTarih = randevuTarih.minusHours(3);

        List<Randevu> randevular = findAllByDateRandevu(threeHoursBeforeRandevuTarih, threeHoursBeforeRandevuTarih.plusHours(3));

        for (Randevu randevu : randevular) {
            sendReminderSms(randevu);
            System.out.println("3 saat önceki SMS hatırlatıcısı gönderiliyor: " + randevu.getId());

        }
    }

    private void sendReminderSms(@NotNull Randevu randevu) {
        YeniHizmet yeniHizmet = yeniHizmetRepository.findById(randevu.getHizmet()).orElse(null);
        Musteri musteri = musteriRepository.findById(randevu.getMusteri()).orElse(null);

        if (musteri != null && yeniHizmet != null) {
            SmsRequest smsRequest = getSmsRequest(randevu, musteri, yeniHizmet);

            // SmsService kullanarak SMS gönderin
            String result = smsService.sendSms(smsRequest);

            // Sonucu loglayın veya ihtiyacınıza göre işleyin
            System.out.println("SMS gönderme sonucu: " + result);
        }
    }

    @NotNull
    private static SmsRequest getSmsRequest(Randevu randevu, Musteri musteri, YeniHizmet yeniHizmet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", new Locale("tr"));
        String formattedDate = randevu.getRandevuTarih().format(formatter);

        String message = "BURCU ATICI GÜZELLİK MERKEZİ\n\n"
                + "Sayın " + musteri.getMusteriAdi()
                + " " + musteri.getMusteriSoyadi()
                + ", " + formattedDate
                + " tarihli " + yeniHizmet.getYeniHizmetAdi()
                + " için randevunuzu hatırlatırız."
                + "Randevunuzun iptali veya değişikliği için"
                + " iletişim numaramızdan bize bilgi vermenizi rica ederiz.\n"
                + "İletişim: " + "05387708048";

        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setApiId("c4fdfaee2461a91318f137e2");
        smsRequest.setApiKey("4edc2818d83ed6d208a53bf8");
        smsRequest.setSender("sms test");
        smsRequest.setMessageType("turkce");
        smsRequest.setMessage(message);
        smsRequest.setMessageContentType("bilgi");
        smsRequest.setPhones(Collections.singletonList(musteri.getMusteriTelNo()));
        smsRequest.setRandevuTarih(randevu.getRandevuTarih());
        return smsRequest;
    }
}
