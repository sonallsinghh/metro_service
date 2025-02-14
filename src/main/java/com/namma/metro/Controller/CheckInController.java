package com.namma.metro.Controller;

import com.namma.metro.Service.CheckInService;
import com.namma.metro.Service.CheckOutService;
import com.namma.metro.Service.StationService;
import com.namma.metro.Service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import com.namma.metro.Entity.Station;
import com.namma.metro.Exception.NoRouteFoundException;

@RestController
@RequestMapping("/metro/check-in")
@CrossOrigin(origins = "*")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;

    @Autowired
    private ActiveUserService activeUserService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<String> checkIn(@RequestParam Long userId, @RequestParam Long stationId) {
        String response = checkInService.checkInUser(userId, stationId);
        activeUserService.addActiveUser(userId);

        String userServiceUrl = "http://localhost:8001/api/users/update-status?userId=" + userId + "&status=ACTIVE";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(userServiceUrl, HttpMethod.PUT, entity, String.class);

        return ResponseEntity.ok(response);
    }
}

