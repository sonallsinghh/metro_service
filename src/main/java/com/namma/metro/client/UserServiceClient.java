package com.namma.metro.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.namma.metro.dto.UserDTO;  // You'll need to create this DTO class
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8001")
public interface UserServiceClient {
    @GetMapping("/api/users/profile/{userId}")
    UserDTO getUserById(@PathVariable("userId") Long userId);

    @PutMapping("api/users/update-status")
    void updateUserStatus(@RequestParam("userId") Long userId, @RequestParam("status") String status);
}
