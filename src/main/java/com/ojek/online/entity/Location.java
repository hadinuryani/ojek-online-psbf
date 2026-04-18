package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(columnDefinition = "DECIMAL(10,8)")
    private Double latitude;

    @Column(columnDefinition = "DECIMAL(11,8)")
    private Double longitude;

    @Column(length = 100)
    private String city;

    @OneToMany(mappedBy = "pickupLocation", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Route> pickupRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "dropoffLocation", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Route> dropoffRoutes = new ArrayList<>();
}
