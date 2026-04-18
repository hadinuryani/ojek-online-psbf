package com.ojek.online.service;

import com.ojek.online.entity.WalletTransaction;
import com.ojek.online.repository.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    public List<WalletTransaction> findAll() {
        return walletTransactionRepository.findAll();
    }

    public Optional<WalletTransaction> findById(Long id) {
        return walletTransactionRepository.findById(id);
    }

    public List<WalletTransaction> findByWalletId(Long walletId) {
        return walletTransactionRepository.findByWallet_Id(walletId);
    }

    public WalletTransaction save(WalletTransaction walletTransaction) {
        return walletTransactionRepository.save(walletTransaction);
    }

    public void deleteById(Long id) {
        walletTransactionRepository.deleteById(id);
    }

    public long count() {
        return walletTransactionRepository.count();
    }
}
