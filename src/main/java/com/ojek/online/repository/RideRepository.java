package com.ojek.online.repository;

import com.ojek.online.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDriverId(Long driverId);
    List<Ride> findByCustomerId(Long customerId);
    List<Ride> findByRideStatusId(Long rideStatusId);
    List<Ride> findByRideStatusName(String statusName);
}
