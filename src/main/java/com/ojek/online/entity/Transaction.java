package com.ojek.online.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Payment payment;

    @Column(nullable = false, unique = true, length = 50)
    private String transactionCode;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 255)
    private String description;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (transactionCode == null) {
            transactionCode = "TRX-" + System.currentTimeMillis();
        }
    }
}
