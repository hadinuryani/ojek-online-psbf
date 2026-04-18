package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(length = 50)
    private String licenseNumber;

    @Column(nullable = false)
    private Boolean isAvailable = false;

    @Column(columnDefinition = "DECIMAL(3,2) DEFAULT 0.00")
    private Double rating = 0.0;

    @Column(nullable = false)
    private Integer totalTrips = 0;

    // PENDING = baru daftar, APPROVED = disetujui admin, REJECTED = ditolak
    @Column(nullable = false, length = 20)
    private String applicationStatus = "PENDING";

    @Column(length = 500)
    private String applicationNote;

    private LocalDateTime appliedAt;

    private LocalDateTime approvedAt;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ride> rides = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DriverPromo> driverPromos = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (appliedAt == null) appliedAt = LocalDateTime.now();
    }
}
