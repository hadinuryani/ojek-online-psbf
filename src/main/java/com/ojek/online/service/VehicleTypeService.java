package com.ojek.online.service;

import com.ojek.online.entity.VehicleType;
import com.ojek.online.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleType> findAll() {
        return vehicleTypeRepository.findAll();
    }

    public Optional<VehicleType> findById(Long id) {
        return vehicleTypeRepository.findById(id);
    }

    public VehicleType save(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public void deleteById(Long id) {
        vehicleTypeRepository.deleteById(id);
    }

    public long count() {
        return vehicleTypeRepository.count();
    }
}
