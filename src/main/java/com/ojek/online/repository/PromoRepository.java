package com.ojek.online.repository;

import com.ojek.online.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
    Optional<Promo> findByCode(String code);
    List<Promo> findByIsActiveTrue();
}
