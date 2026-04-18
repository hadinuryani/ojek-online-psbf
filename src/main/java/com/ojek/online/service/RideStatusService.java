package com.ojek.online.service;

import com.ojek.online.entity.RideStatus;
import com.ojek.online.repository.RideStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideStatusService {

    private final RideStatusRepository rideStatusRepository;

    public List<RideStatus> findAll() {
        return rideStatusRepository.findAll();
    }

    public Optional<RideStatus> findById(Long id) {
        return rideStatusRepository.findById(id);
    }

    public Optional<RideStatus> findByName(String name) {
        return rideStatusRepository.findByName(name);
    }

    public RideStatus save(RideStatus rideStatus) {
        return rideStatusRepository.save(rideStatus);
    }

    public void deleteById(Long id) {
        rideStatusRepository.deleteById(id);
    }

    public long count() {
        return rideStatusRepository.count();
    }
}
