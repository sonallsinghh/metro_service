package com.namma.metro.Controller;

import com.namma.metro.Entity.Station;
import com.namma.metro.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metro/stations")
@CrossOrigin(origins = "*")
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping
    public ResponseEntity<List<Station>> getStations() {
        return ResponseEntity.ok(stationService.getStations());
    }
}
