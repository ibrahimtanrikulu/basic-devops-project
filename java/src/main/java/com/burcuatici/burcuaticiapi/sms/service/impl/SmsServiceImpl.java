package com.burcuatici.burcuaticiapi.sms.service.impl;

import com.burcuatici.burcuaticiapi.model.entity.Sms;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateSmsRequest;
import com.burcuatici.burcuaticiapi.repository.SmsRepository;
import com.burcuatici.burcuaticiapi.sms.request.SmsRequest;
import com.burcuatici.burcuaticiapi.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@Service
class SmsServiceImpl implements SmsService {

    @Value("${sms.api.url}")
    private String apiUrl;

    @Value("${sms.api.id}")
    private String apiId;

    @Value("${sms.api.key}")
    private String apiKey;

    private final SmsRepository smsRepository;


    public SmsServiceImpl(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Override
    public List<Sms> findAll() {
        return smsRepository.findAll();
    }

    @Override
    public Sms create(CreateSmsRequest request) {
        Sms sms = request.toSms();
        return smsRepository.save(sms);
    }

    @Override
    public void deleteById(Long id) {
        smsRepository.deleteById(id);
    }

    @Override
    public String sendSms(SmsRequest smsRequest) {
        try {
            URL url = new URL(apiUrl);

            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setDoOutput(true);
            connect.setConnectTimeout(5000);
            connect.setDoInput(true);
            connect.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connect.setRequestMethod("POST");

            // Convert SmsRequest to JSON string
            String jsonFormData = convertObjectToJson(smsRequest);

            OutputStream prepareFormData = connect.getOutputStream();
            prepareFormData.write(jsonFormData.getBytes(StandardCharsets.UTF_8));
            prepareFormData.close();

            InputStream inputStream = new BufferedInputStream(connect.getInputStream());
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            inputStream.close();
            connect.disconnect();

            return result;

        } catch (Exception e) {
            return "Bir hata ile karşılaşıldı : " + e.getMessage();
        }
    }

    private String convertObjectToJson(Object object) {
        // JSON dönüşümü için
        return "{ " + "\"api_id\":\"" + this.apiId + "\","
                + "\"api_key\":\"" + this.apiKey + "\","
                + "\"sender\":\"" + "test1" + "\","
                +"\"message_type\":\"" + "normal" + "\","
                + "\"message\":\"" + "test1" + "\","
                + "\"message_content_type\":\"" + "test2" + "\","
                + "\"phones\":" + "[5464167749]" + "}";
    }

}
