package com.namma.metro.Controller;

import com.namma.metro.Exception.NoRouteFoundException;
import com.namma.metro.Service.ActiveUserService;
import com.namma.metro.Service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/metro/check-out")
@CrossOrigin(origins = "*")
public class CheckOutController {
    @Autowired
    private CheckOutService checkOutService;

    @Autowired
    private ActiveUserService activeUserService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<String> checkOut(@RequestParam Long userId, @RequestParam Long stationId) {
        try {
            String response = checkOutService.checkOutUser(userId, stationId);
            activeUserService.removeActiveUser(userId);

            String userServiceUrl = "http://localhost:8001/api/users/update-status?userId=" + userId + "&status=INACTIVE";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(userServiceUrl, HttpMethod.PUT, entity, String.class);

            return ResponseEntity.ok(response);
        } catch (NoRouteFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check-out failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/fare")
    public Double getFare(@RequestParam Long userId) {
        return checkOutService.getLatestFare(userId); // ✅ Call method using instance, not statically
    }
}
