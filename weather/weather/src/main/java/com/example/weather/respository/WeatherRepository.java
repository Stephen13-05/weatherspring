package com.example.weather.respository;

import com.example.weather.model.weathertable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import java.time.LocalDate;

@Repository
public interface WeatherRepository extends JpaRepository<weathertable, Long> {

    // Spring Data JPA automatically provides basic CRUD operations:
    // save(), findById(), findAll(), deleteById(), etc.

    List<weathertable> findByDate(LocalDate date);

    List<weathertable> findByMonthAndYear(Integer month,Integer year);

}