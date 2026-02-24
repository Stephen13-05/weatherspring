package com.example.weather.util;

import com.example.weather.model.weathertable;
import com.example.weather.respository.WeatherRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvLoader {

    private final WeatherRepository repository;

    public CsvLoader(WeatherRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadData() throws Exception {
        if (repository.count() > 0) {
            return;
        }

        List<weathertable> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");
        char separator = detectSeparator();

        ClassPathResource resource = new ClassPathResource("testset.csv");
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(resource.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                .build()) {
            reader.readNext(); // skip header

            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length <= 11) {
                    continue;
                }

                try {
                    weathertable weather = new weathertable();

                    LocalDateTime dateTime = LocalDateTime.parse(line[0], formatter);
                    LocalDate date = dateTime.toLocalDate();

                    weather.setDate(date);
                    weather.setWeatherCondition(line[1]);
                    weather.setHumidity(Integer.parseInt(line[6]));
                    weather.setPressure(Integer.parseInt(line[8]));
                    weather.setTemperature(Double.parseDouble(line[11]));
                    weather.setMonth(date.getMonthValue());
                    weather.setYear(date.getYear());

                    list.add(weather);
                } catch (Exception ignored) {
                    // Skip invalid rows instead of failing application startup.
                }
            }
        }

        repository.saveAll(list);
    }

    private char detectSeparator() throws Exception {
        ClassPathResource resource = new ClassPathResource("testset.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String header = br.readLine();
            if (header == null) {
                return ',';
            }
            return header.contains("\t") ? '\t' : ',';
        }
    }
}
