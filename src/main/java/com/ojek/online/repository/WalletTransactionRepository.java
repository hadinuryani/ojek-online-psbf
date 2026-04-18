package com.ojek.online.repository;

import com.ojek.online.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
    List<WalletTransaction> findByWallet_Id(Long walletId);
    List<WalletTransaction> findByType(String type);
}
