package com.namma.metro.Service;

import com.namma.metro.Entity.Station;
import com.namma.metro.Exception.StationNotFoundException;
import com.namma.metro.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    public List<Station> getStations() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            throw new StationNotFoundException("No stations found in the system.");
        }
        return stations;
    }
}
