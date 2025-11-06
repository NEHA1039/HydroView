package com.hydroview.hydroview_api.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.hydroview.hydroview_api.repository.StationRepository;
import com.hydroview.hydroview_api.repository.WaterLevelReadingRepository;
import com.hydroview.hydroviewapi.model.Station;
import com.hydroview.hydroviewapi.model.WaterLevelReading;

@Component
public class DataSeeder implements CommandLineRunner {

    private final StationRepository stationRepository;
    private final WaterLevelReadingRepository waterLevelReadingRepository;

    public DataSeeder(StationRepository stationRepository, WaterLevelReadingRepository waterLevelReadingRepository) {
        this.stationRepository = stationRepository;
        this.waterLevelReadingRepository = waterLevelReadingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (stationRepository.count() == 0) {
            System.out.println("Database is empty, seeding data...");
            loadData();
        } else {
            System.out.println("Database already contains data, skipping seed.");
        }
    }

    private void loadData() {
        Map<String, Station> stationCache = new HashMap<>();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource("sample_data.csv").getInputStream()))) {
            
            String line;
            reader.readLine(); 

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); 

                String stationId = data[0].trim(); 
                String stationName = data[1].trim(); 
                double latitude = Double.parseDouble(data[2].trim()); 
                double longitude = Double.parseDouble(data[3].trim()); 
                String dateString = data[4].trim(); 
                double waterLevel = Double.parseDouble(data[5].trim()); 

                Station station = stationCache.get(stationId);

                if (station == null) {
                    station = stationRepository.findByStationId(stationId); 
                    if (station == null) { 
                        station = new Station();
                        station.setStationId(stationId);
                        station.setStationName(stationName);
                        station.setLatitude(latitude);
                        station.setLongitude(longitude);
                        station = stationRepository.save(station); 
                    }
                    stationCache.put(stationId, station); 
                }

                WaterLevelReading reading = new WaterLevelReading();
                reading.setReadingDate(LocalDate.parse(dateString, dateFormatter));
                reading.setWaterLevel(waterLevel);
                reading.setStation(station); 

                waterLevelReadingRepository.save(reading); 
            }
            System.out.println("Data seeding complete!");

        } catch (Exception e) {
            System.err.println("Error while seeding data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}