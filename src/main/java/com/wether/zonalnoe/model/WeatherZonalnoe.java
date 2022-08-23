package com.wether.zonalnoe.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class WeatherZonalnoe implements Weather {
    Document doc;


    public WeatherZonalnoe() {
    }

    @Override
    public String takeTheWhether(String url) {


        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elementsTemp = doc.getElementsByClass("unit unit_temperature_c");
       // Elements dayOfWeek = doc.getElementsByClass("day");

        int day = getDayNumberNew(LocalDate.now());
        String className = "date date-" + day;

        Elements elementsDataDay = doc.getElementsByClass(className);
        Element wheather = doc.getElementsByAttribute("data-text").get(1);

        String wheatherToDay = wheather.attr("data-text").toString();
        //String dayWeek = dayOfWeek.attr("class", "day").get(1).text();
        String dayData = elementsDataDay.attr("calass", className).get(0).text();
        String dayTemp = elementsTemp.attr("class", "unit unit_temperature_c").get(3).text();
        String nightTemp = elementsTemp.attr("class", "unit unit_temperature_c").get(2).text();

        StringBuilder sb = new StringBuilder();
        String end = sb.append(dayData).append("\n")
                .append("ночь ")
                .append(dayTemp).append(" день ")
                .append(nightTemp).append("\n")
                .append(wheatherToDay).toString();
        System.out.println(end);
        return end;
    }

    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }
}
