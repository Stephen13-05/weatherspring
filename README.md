# 🌦 Weather Data Analysis API

A Spring Boot REST API that ingests historical weather data from a CSV file into a MySQL database and exposes endpoints to retrieve weather information and monthly temperature statistics.

---

## 🚀 Tech Stack

- Java 21  
- Spring Boot  
- Spring Data JPA  
- MySQL 8  
- OpenCSV  
- Lombok  
- Maven  

---

## 📌 Features

- Automatic CSV data loading on application startup  
- MySQL database integration using JPA  
- Fetch weather data by specific date  
- Fetch weather data by month and year  
- Monthly temperature statistics:
  - Minimum Temperature  
  - Maximum Temperature  
  - Median Temperature  

---

## 🏗 Architecture

Controller → Service → Repository → Database

```
com.example.weather
│
├── controller
│   └── WeatherController.java
│
├── service
│   └── WeatherService.java
│
├── repository
│   └── WeatherRepository.java
│
├── model
│   └── Weathertable.java
│
├── util
│   └── CsvLoader.java
│
└── WeatherApplication.java
```

---

## ⚙️ Database Configuration

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/weathertable
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Make sure:
- MySQL server is running
- Database `weathertable` is created

---

## 📥 CSV Setup

Place your CSV file inside:

```
src/main/resources/testset.csv
```

The application automatically loads the CSV using `@PostConstruct` inside `CsvLoader`.

---

## 🗄 Database Schema

The application automatically creates the following table:

```sql
CREATE TABLE weather (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    temperature DOUBLE,
    humidity INT,
    pressure INT,
    weather_condition VARCHAR(255),
    month INT,
    year INT
);
```

---

## 🌐 REST API Endpoints

### 1️⃣ Get Weather By Date

```
GET http://localhost:8080/weather/date?date=1996-11-01
```

---

### 2️⃣ Get Weather By Month & Year

```
GET http://localhost:8080/weather/month?month=11&year=1996
```

---

### 3️⃣ Get Monthly Statistics

```
GET http://localhost:8080/weather/stats?month=11&year=1996
```

### Sample Response

```json
{
  "min": 20.0,
  "max": 35.0,
  "median": 27.5
}
```

---

## ▶️ Running the Application

1. Clone the repository

```
git clone <your-repository-url>
```

2. Navigate into the project

```
cd weather
```

3. Run the application

```
mvn spring-boot:run
```

OR run `WeatherApplication.java` from your IDE.

The application will start at:

```
http://localhost:8080
```

---
