package com.example.weather.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "weather")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class weathertable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private Double temperature;
    private Integer humidity;
    private Integer pressure;

    @Column(name = "weather_condition")
    private String weatherCondition;

    private Integer month;
    private Integer year;
}
