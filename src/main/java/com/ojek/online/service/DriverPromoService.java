package com.ojek.online.service;

import com.ojek.online.entity.DriverPromo;
import com.ojek.online.repository.DriverPromoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverPromoService {

    private final DriverPromoRepository driverPromoRepository;

    public List<DriverPromo> findAll() {
        return driverPromoRepository.findAll();
    }

    public Optional<DriverPromo> findById(Long id) {
        return driverPromoRepository.findById(id);
    }

    public List<DriverPromo> findByDriverId(Long driverId) {
        return driverPromoRepository.findByDriverId(driverId);
    }

    public DriverPromo save(DriverPromo driverPromo) {
        return driverPromoRepository.save(driverPromo);
    }

    public void deleteById(Long id) {
        driverPromoRepository.deleteById(id);
    }

    public long count() {
        return driverPromoRepository.count();
    }
}
