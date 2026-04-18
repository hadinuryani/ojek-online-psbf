package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id", nullable = false, unique = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ride ride;

    private LocalDateTime bookingTime;

    @Column(nullable = false, length = 30)
    private String status = "PENDING";

    @Column(length = 500)
    private String notes;

    @Column(length = 255)
    private String pickupAddress;

    @Column(length = 255)
    private String dropoffAddress;

    @PrePersist
    protected void onCreate() {
        bookingTime = LocalDateTime.now();
    }
}
