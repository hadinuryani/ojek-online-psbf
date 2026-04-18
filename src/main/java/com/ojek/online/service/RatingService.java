package com.ojek.online.service;

import com.ojek.online.entity.Rating;
import com.ojek.online.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {

    private final RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    public List<Rating> findByDriverId(Long driverId) {
        return ratingRepository.findByDriver_Id(driverId);
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }

    public long count() {
        return ratingRepository.count();
    }
}
