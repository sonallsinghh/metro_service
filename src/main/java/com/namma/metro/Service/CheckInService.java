package com.namma.metro.Service;

import com.namma.metro.Entity.CheckIn;
import com.namma.metro.Entity.Station;
import com.namma.metro.Repository.CheckInRepository;
import com.namma.metro.Repository.StationRepository;
import com.namma.metro.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private RestTemplate restTemplate; // Use RestTemplate instead of Feign

    public String checkInUser(Long userId, Long stationId) {
        try {
            // Step 1: Validate if user exists via User Service
            String userServiceUrl = "http://localhost:8001/api/users/profile/" + userId;

            ResponseEntity<UserDTO> response = restTemplate.exchange(
                    userServiceUrl, HttpMethod.GET, null, UserDTO.class
            );

            UserDTO user = response.getBody();
            if (user == null) {
                return "User not found!";
            }

            // Step 2: Check if user is already checked in
            Optional<CheckIn> existingCheckIn = checkInRepository.findByUserId(userId);
            if (existingCheckIn.isPresent()) {
                return "User is already checked in!";
            }

            // Step 3: Validate Station
            Optional<Station> station = stationRepository.findById(stationId);
            if (station.isEmpty()) {
                return "Station not found!";
            }

            // Step 4: Create and Save Check-In Record
            CheckIn checkIn = new CheckIn();
            checkIn.setUserId(userId);
            checkIn.setStation(station.get());
            checkInRepository.save(checkIn);

            return "Check-in successful at station " + station.get().getName();
        } catch (Exception e) {
            return "Error connecting to User Service: " + e.getMessage();
        }
    }
}
