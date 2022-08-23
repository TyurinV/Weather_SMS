package com.wether.zonalnoe.service;

import com.wether.zonalnoe.model.WeatherZonalnoe;
import com.wether.zonalnoe.utils.Smsc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendSMS {
    WeatherZonalnoe weatherZonalnoe;


    @Value("${zonalnoe.url}")
    String url;
    @Value("${phone.numbers}")
    String number;

    public SendSMS() {
    }

    @Autowired
    public SendSMS(WeatherZonalnoe weatherZonalnoe) {
        this.weatherZonalnoe = weatherZonalnoe;
    }

    @Scheduled(cron = "${interval-in-cron}")
    public void sendSms() throws IOException {

        String message = weatherZonalnoe.takeTheWhether(url);
        new Smsc("Testikov", "!ENmuDwSnfW4SP!").send_sms(number, message, 0, "", "", 0, "+79230060074", "");
    }
}
