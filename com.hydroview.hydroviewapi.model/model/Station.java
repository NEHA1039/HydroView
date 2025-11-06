package com.hydroview.hydroviewapi.model;

import java.util.List;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stationId; // From the CSV

    private String stationName;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WaterLevelReading> readings;

    public Station() {
    }

    public Station(String stationId, String stationName, double latitude, double longitude) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<WaterLevelReading> getReadings() {
        return readings;
    }

    public void setReadings(List<WaterLevelReading> readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }

}