package com.ojek.online.service;

import com.ojek.online.entity.Ride;
import com.ojek.online.entity.RideStatus;
import com.ojek.online.repository.RideRepository;
import com.ojek.online.repository.RideStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideService {

    private final RideRepository rideRepository;
    private final RideStatusRepository rideStatusRepository;

    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    public Optional<Ride> findById(Long id) {
        return rideRepository.findById(id);
    }

    public List<Ride> findByDriverId(Long driverId) {
        return rideRepository.findByDriverId(driverId);
    }

    public List<Ride> findByCustomerId(Long customerId) {
        return rideRepository.findByCustomerId(customerId);
    }

    public List<Ride> findByStatus(String statusName) {
        return rideRepository.findByRideStatusName(statusName);
    }

    public Ride save(Ride ride) {
        return rideRepository.save(ride);
    }

    public Ride acceptRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        RideStatus accepted = rideStatusRepository.findByName("ACCEPTED")
                .orElseThrow(() -> new RuntimeException("Status ACCEPTED not found"));
        ride.setRideStatus(accepted);
        ride.setStartTime(LocalDateTime.now());
        return rideRepository.save(ride);
    }

    public Ride completeRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        RideStatus completed = rideStatusRepository.findByName("COMPLETED")
                .orElseThrow(() -> new RuntimeException("Status COMPLETED not found"));
        ride.setRideStatus(completed);
        ride.setEndTime(LocalDateTime.now());
        return rideRepository.save(ride);
    }

    public void deleteById(Long id) {
        rideRepository.deleteById(id);
    }

    public long count() {
        return rideRepository.count();
    }
}
