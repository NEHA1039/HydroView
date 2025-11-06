package com.hydroview.hydroviewapi.model;

import java.time.LocalDate;

@Entity
@Table(name = "water_readings")
public class WaterLevelReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate readingDate;
    private double waterLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    // Default constructor required by JPA
    public WaterLevelReading() {
    }

    public WaterLevelReading(LocalDate readingDate, double waterLevel, Station station) {
        this.readingDate = readingDate;
        this.waterLevel = waterLevel;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "WaterLevelReading{" +
                "id=" + id +
                ", readingDate=" + readingDate +
                ", waterLevel=" + waterLevel +
                ", station=" + (station != null ? station.getId() : null) +
                '}';
    }

}