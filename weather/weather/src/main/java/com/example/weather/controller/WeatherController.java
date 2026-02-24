package com.example.weather.controller;
import com.example.weather.model.weathertable;

import com.example.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    // 1️⃣ Get weather by exact date
    @GetMapping("/date")
    public List<weathertable> getByDate(@RequestParam String date) {
        return service.findByDate(LocalDate.parse(date));
    }

    // 2️⃣ Get weather by month and year
    @GetMapping("/month")
    public List<weathertable> getByMonth(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return service.findByMonthAndYear(month, year);
    }

    // 3️⃣ Get monthly statistics
    @GetMapping("/stats")
    public Map<String, Double> getMonthlyStats(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return service.getMonthyStatistics(month, year);
    }
}
