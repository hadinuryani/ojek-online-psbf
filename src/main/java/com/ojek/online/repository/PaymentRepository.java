package com.ojek.online.repository;

import com.ojek.online.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByRide_Id(Long rideId);
    List<Payment> findByStatus(String status);
    List<Payment> findByPaymentMethod_Id(Long paymentMethodId);
}
