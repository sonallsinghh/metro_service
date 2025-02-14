package com.namma.metro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "checkouts")
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false) // ✅ Manually storing user ID
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @CreationTimestamp
    @Column(name = "checkout_time", updatable = false)
    private Timestamp checkoutTime;

    private Double fare;

    // Manually added Getters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Station getStation() {
        return station;
    }

    public Timestamp getCheckoutTime() {
        return checkoutTime;
    }

    public Double getFare() {
        return fare;
    }

    // Manually added Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setCheckoutTime(Timestamp checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}
