package com.namma.metro.Service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ActiveUserService {
    private final Set<Long> activeUsers = new HashSet<>();

    public void addActiveUser(Long userId) {
        activeUsers.add(userId);
    }

    public void removeActiveUser(Long userId) {
        activeUsers.remove(userId);
    }

    public Set<Long> getActiveUsers() {
        return new HashSet<>(activeUsers);
    }
}
