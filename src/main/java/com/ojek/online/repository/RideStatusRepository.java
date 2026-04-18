package com.ojek.online.repository;

import com.ojek.online.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RideStatusRepository extends JpaRepository<RideStatus, Long> {
    Optional<RideStatus> findByName(String name);
}
