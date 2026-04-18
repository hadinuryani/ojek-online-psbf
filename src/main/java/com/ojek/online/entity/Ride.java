package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_status_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private RideStatus rideStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private Double fare;

    @Column(length = 255)
    private String notes;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Booking booking;

    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Rating rating;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
