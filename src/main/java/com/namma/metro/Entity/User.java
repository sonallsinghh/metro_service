//package com.namma.metro.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "users")
//@Getter
//@Setter
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @Column(unique = true, nullable = true) // Allow one of them to be NULL
//    private String metroCard;
//
//    @Column(unique = true, nullable = true)
//    private String qrCode;
//
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//    @PrePersist
//    @PreUpdate
//    private void validateCardOrQR() {
//        if ((metroCard != null && qrCode != null) || (metroCard == null && qrCode == null)) {
//            throw new IllegalStateException("User must have either a metroCard or a qrCode, but not both!");
//        }
//    }
//}
