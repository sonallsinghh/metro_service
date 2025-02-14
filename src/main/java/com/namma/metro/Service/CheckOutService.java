package com.namma.metro.Service;

import com.namma.metro.Entity.*;
import com.namma.metro.Repository.*;
import com.namma.metro.client.UserServiceClient;
import com.namma.metro.dto.UserDTO;
import com.namma.metro.Exception.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CheckOutService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private CheckOutRepository checkOutRepository;  // ✅ Removed static

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    public String checkOutUser(Long userId, Long stationId) {
        try {
            // Validate if user exists via User Service
            UserDTO user = userServiceClient.getUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found!");
            }

            Optional<CheckIn> checkIn = checkInRepository.findByUserId(userId);
            if (checkIn.isEmpty()) {
                throw new UserNotCheckedInException("User has not checked in!");
            }

            Optional<Station> endStation = stationRepository.findById(stationId);
            if (endStation.isEmpty()) {
                throw new StationNotFoundException("Station not found!");
            }

            Station startStation = checkIn.get().getStation();
            Route route = routeRepository.findRoute(startStation.getId(), stationId);
            if (route == null) {
                throw new NoRouteFoundException("No direct route found!");
            }

            double fare = calculateFare(route.getDistanceKm());

            CheckOut checkOut = new CheckOut();
            checkOut.setUserId(userId);
            checkOut.setStation(endStation.get());
            checkOut.setFare(fare);
            checkOutRepository.save(checkOut);

            checkInRepository.delete(checkIn.get());

            return "Check-out successful! Fare: ₹" + fare;

        } catch (FeignException e) {
            throw new UserServiceException("Error connecting to User Service: " + e.getMessage());
        }
    }

    private double calculateFare(double distance) {
        return distance * 10;
    }

    public Double getLatestFare(Long userId) {  // ✅ Removed static
        CheckOut latestCheckout = checkOutRepository.findTopByUserIdOrderByCheckoutTimeDesc(userId);
        return (latestCheckout != null) ? latestCheckout.getFare() : 0.0;
    }
}
