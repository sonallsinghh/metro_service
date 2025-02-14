//package com.namma.metro.Controller;
//
//import com.namma.metro.Entity.Station;
//import com.namma.metro.Exception.NoRouteFoundException;
//import com.namma.metro.Service.ActiveUserService;
//import com.namma.metro.Service.CheckInService;
//import com.namma.metro.Service.CheckOutService;
//import com.namma.metro.Service.StationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//
//
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/metro")
//@CrossOrigin(origins = "*")
//public class MetroController {
//    @Autowired
//    private CheckInService checkInService;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private CheckOutService checkOutService;
//
//    @Autowired
//    private StationService stationService;
//
//    //    private SOSService sosService;
//
//    @Autowired
//    private ActiveUserService activeUserService;
//
//    @PostMapping("/check-in")
//    public ResponseEntity<String> checkIn(@RequestParam Long userId, @RequestParam Long stationId) {
//        String response = checkInService.checkInUser(userId, stationId);
//        activeUserService.addActiveUser(userId);  // Adding the user to active users
//
//        // Define user service URL
//        String userServiceUrl = "http://localhost:8001/api/users/update-status?userId=" + userId + "&status=ACTIVE";
//
//        // Set headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Create an empty request entity with headers
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Make the PUT request using RestTemplate
//        ResponseEntity<String> userServiceResponse = restTemplate.exchange(userServiceUrl, HttpMethod.PUT, entity, String.class);
//
//        // Log response from user service
//        System.out.println("Response from user service: " + userServiceResponse.getBody());
//
//        return ResponseEntity.ok(response);
//    }
//
//
//
//    @PostMapping("/check-out")
//    public ResponseEntity<String> checkOut(@RequestParam Long userId, @RequestParam Long stationId) {
//        try {
//            System.out.println("Checking out user: " + userId + " from station: " + stationId);
//            String response = checkOutService.checkOutUser(userId, stationId);
//            activeUserService.removeActiveUser(userId);
//
//            // Update status to INACTIVE
//            String userServiceUrl = "http://localhost:8001/api/users/update-status?userId=" + userId + "&status=INACTIVE";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            ResponseEntity<String> userServiceResponse = restTemplate.exchange(userServiceUrl, HttpMethod.PUT, entity, String.class);
//            System.out.println("Response from user service: " + userServiceResponse.getBody());
//
//            return ResponseEntity.ok(response);
//        } catch (NoRouteFoundException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check-out failed: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
//        }
//    }
//
//
//
//
//    @GetMapping("/stations")
//    public ResponseEntity<List<Station>> getStations() {
//        return ResponseEntity.ok(stationService.getStations());
//    }
//
////    @PostMapping("/sos")
////    public ResponseEntity<String> triggerSOS(@RequestParam Long userId, @RequestParam Long stationId) {
////        return ResponseEntity.ok(sosService.triggerSOS(userId, stationId));
////    }
//
//
//}
//
