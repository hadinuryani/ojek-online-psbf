package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_location_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location pickupLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dropoff_location_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location dropoffLocation;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Integer estimatedTime;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ride> rides = new ArrayList<>();
}
