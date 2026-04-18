package com.ojek.online.service;

import com.ojek.online.entity.Promo;
import com.ojek.online.repository.PromoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PromoService {

    private final PromoRepository promoRepository;

    public List<Promo> findAll() {
        return promoRepository.findAll();
    }

    public Optional<Promo> findById(Long id) {
        return promoRepository.findById(id);
    }

    public List<Promo> findActive() {
        return promoRepository.findByIsActiveTrue();
    }

    public Optional<Promo> findByCode(String code) {
        return promoRepository.findByCode(code);
    }

    public Promo save(Promo promo) {
        return promoRepository.save(promo);
    }

    public void deleteById(Long id) {
        promoRepository.deleteById(id);
    }

    public long count() {
        return promoRepository.count();
    }
}
