package com.ojek.online.repository;

import com.ojek.online.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByPickupLocationId(Long pickupLocationId);
    List<Route> findByDropoffLocationId(Long dropoffLocationId);
}
