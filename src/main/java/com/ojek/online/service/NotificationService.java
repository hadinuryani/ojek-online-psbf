package com.ojek.online.service;

import com.ojek.online.entity.Notification;
import com.ojek.online.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> findByUserId(Long userId) {
        return notificationRepository.findByUser_Id(userId);
    }

    public List<Notification> findUnreadByUserId(Long userId) {
        return notificationRepository.findByUser_IdAndIsReadFalse(userId);
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    public long count() {
        return notificationRepository.count();
    }
}
