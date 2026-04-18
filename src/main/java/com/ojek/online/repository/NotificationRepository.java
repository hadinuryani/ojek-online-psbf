package com.ojek.online.repository;

import com.ojek.online.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser_Id(Long userId);
    List<Notification> findByUser_IdAndIsReadFalse(Long userId);
    long countByUser_IdAndIsReadFalse(Long userId);
}
