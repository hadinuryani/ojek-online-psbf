package com.ojek.online.repository;

import com.ojek.online.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUserId(Long userId);
    List<Driver> findByIsAvailableTrue();
    List<Driver> findByIsAvailableTrueAndApplicationStatus(String applicationStatus);
    List<Driver> findByApplicationStatus(String applicationStatus);
    Optional<Driver> findByLicenseNumber(String licenseNumber);
    boolean existsByUserId(Long userId);
}
