package com.ojek.online.repository;

import com.ojek.online.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionCode(String transactionCode);
    List<Transaction> findByPayment_Id(Long paymentId);
    List<Transaction> findByType(String type);
}
