package com.ojek.online.repository;

import com.ojek.online.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer_Id(Long customerId);
    List<Booking> findByStatus(String status);
    Optional<Booking> findByRide_Id(Long rideId);
}
