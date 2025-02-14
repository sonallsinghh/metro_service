package com.namma.metro.Repository;

import com.namma.metro.Entity.Route;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.startStation.id = :startId AND r.endStation.id = :endId")
    Route findRoute(@Param("startId") Long startId, @Param("endId") Long endId);
}

