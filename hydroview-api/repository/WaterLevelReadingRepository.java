package com.hydroview.hydroview_api.repository;

import com.hydroview.hydroviewapi.model.WaterLevelReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterLevelReadingRepository extends JpaRepository<WaterLevelReading, Long> {
    
}