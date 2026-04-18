package com.ojek.online.service;

import com.ojek.online.entity.UserRole;
import com.ojek.online.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    public Optional<UserRole> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleRepository.findByRole_Id(roleId);
    }

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }

    public long count() {
        return userRoleRepository.count();
    }
}
