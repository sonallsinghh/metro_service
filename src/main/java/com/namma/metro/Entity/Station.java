package com.namma.metro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stations")
@Getter
@Setter
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "active")
    private Boolean isActive = true;

    @Column(name = "station_master_email")
    private String stationMasterEmail;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

}
