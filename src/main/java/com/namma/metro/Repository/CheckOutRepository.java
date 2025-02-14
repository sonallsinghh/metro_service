package com.namma.metro.Repository;

import com.namma.metro.Entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {
    CheckOut findTopByUserIdOrderByCheckoutTimeDesc(Long userId);
}

