package com.ojek.online.service;

import com.ojek.online.entity.Wallet;
import com.ojek.online.entity.WalletTransaction;
import com.ojek.online.repository.WalletRepository;
import com.ojek.online.repository.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;

    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }

    public Optional<Wallet> findByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet topUp(Long walletId, Double amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction wt = WalletTransaction.builder()
                .wallet(wallet)
                .type("TOP_UP")
                .amount(amount)
                .description("Top up Rp " + amount)
                .build();
        walletTransactionRepository.save(wt);

        return walletRepository.save(wallet);
    }

    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }

    public long count() {
        return walletRepository.count();
    }
}
