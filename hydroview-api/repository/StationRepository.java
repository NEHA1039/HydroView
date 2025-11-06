package com.hydroview.hydroview_api.repository;

import com.hydroview.hydroviewapi.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

    Station findByStationId(String stationId);
}