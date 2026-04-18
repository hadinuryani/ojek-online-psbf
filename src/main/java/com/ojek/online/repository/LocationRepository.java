package com.ojek.online.repository;

import com.ojek.online.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByCity(String city);
    List<Location> findByNameContainingIgnoreCase(String name);
}
