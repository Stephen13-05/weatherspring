package com.example.weather.service;

import com.example.weather.model.weathertable;
import com.example.weather.respository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.*;

@Service
public class WeatherService {
    private final WeatherRepository repository;
    public WeatherService(WeatherRepository repository){
        this.repository = repository;
    }
    //1. gets weather by exact date
    public List<weathertable> findByDate(LocalDate date){
        return repository.findByDate(date);
    }
    public List<weathertable> findByMonthAndYear(Integer month,Integer year){
        return repository.findByMonthAndYear(month,year);
    }
    // to get min max median temperatures
    public Map<String,Double> getMonthyStatistics(Integer month, Integer year){
        List<weathertable> data = repository.findByMonthAndYear(month,year);
        if(data.isEmpty()) {
            throw new RuntimeException("No data found");
        }
        List<Double> temps = data.stream()
                .map(weathertable::getTemperature)
                .sorted()
                .toList();

        // 4. Calculate Min and Max
        double min = temps.get(0);
        double max = temps.get(temps.size() - 1);

        // 5. Calculate Median
        double median;
        int size = temps.size();
        if (size % 2 == 0) {
            median = (temps.get(size / 2 - 1) + temps.get(size / 2)) / 2.0;
        } else {
            median = temps.get(size / 2);
        }

        // 6. Return as a Map or a custom DTO
        Map<String, Double> stats = new HashMap<>();
        stats.put("min", min);
        stats.put("max", max);
        stats.put("median", median);

        return stats;
    }
}
