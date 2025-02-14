package com.namma.metro.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "checkins")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "user_id", nullable = false)// Manually storing user ID
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @CreationTimestamp
    @Column(name = "checkin_time", updatable = false) // Prevent updates to this field
    private Timestamp checkinTime;

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

    public Timestamp getCheckinTime() {
        return checkinTime;
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

    public void setCheckinTime(Timestamp checkinTime) {
        this.checkinTime = checkinTime;
    }
}
