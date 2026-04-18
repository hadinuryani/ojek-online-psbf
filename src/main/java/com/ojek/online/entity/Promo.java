package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Double discountPercent;

    @Column(nullable = false)
    private Double maxDiscount;

    private LocalDateTime validFrom;

    private LocalDateTime validUntil;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "promo", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DriverPromo> driverPromos = new ArrayList<>();
}
