package com.ojek.online.repository;

import com.ojek.online.entity.DriverPromo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DriverPromoRepository extends JpaRepository<DriverPromo, Long> {
    List<DriverPromo> findByDriverId(Long driverId);
    List<DriverPromo> findByPromoId(Long promoId);
}
