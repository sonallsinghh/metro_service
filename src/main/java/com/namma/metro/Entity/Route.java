package com.namma.metro.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "start_station_id", nullable = false)
    private Station startStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id", nullable = false)
    private Station endStation;

    private Double distanceKm;

    // Manually added Getters
    public Long getId() {
        return id;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    // Manually added Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }
}
