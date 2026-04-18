package com.ojek.online.service;

import com.ojek.online.entity.Driver;
import com.ojek.online.entity.Role;
import com.ojek.online.entity.UserRole;
import com.ojek.online.repository.DriverRepository;
import com.ojek.online.repository.RoleRepository;
import com.ojek.online.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    public Optional<Driver> findByUserId(Long userId) {
        return driverRepository.findByUserId(userId);
    }

    public List<Driver> findAvailableDrivers() {
        return driverRepository.findByIsAvailableTrueAndApplicationStatus("APPROVED");
    }

    public List<Driver> findApprovedDrivers() {
        return driverRepository.findByApplicationStatus("APPROVED");
    }

    public List<Driver> findPendingApplications() {
        return driverRepository.findByApplicationStatus("PENDING");
    }

    public boolean existsByUserId(Long userId) {
        return driverRepository.existsByUserId(userId);
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver approveDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setApplicationStatus("APPROVED");
        driver.setIsAvailable(true);
        driver.setApprovedAt(LocalDateTime.now());

        // Add DRIVER role to the user
        Role driverRole = roleRepository.findByName("DRIVER")
                .orElseThrow(() -> new RuntimeException("Role DRIVER not found"));
        boolean hasRole = userRoleRepository.findByUser_Id(driver.getUser().getId()).stream()
                .anyMatch(ur -> ur.getRole().getName().equals("DRIVER"));
        if (!hasRole) {
            userRoleRepository.save(UserRole.builder()
                    .user(driver.getUser())
                    .role(driverRole)
                    .build());
        }

        return driverRepository.save(driver);
    }

    public Driver rejectDriver(Long driverId, String reason) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setApplicationStatus("REJECTED");
        driver.setApplicationNote(reason);
        return driverRepository.save(driver);
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    public long count() {
        return driverRepository.count();
    }

    public long countPending() {
        return driverRepository.findByApplicationStatus("PENDING").size();
    }
}
