package com.devrodts.authservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devrodts.authservice.entity.User;
import com.devrodts.authservice.entity.UserIpAddress;

@Repository
public interface UserIpAddressRepository extends JpaRepository<UserIpAddress, UUID> {
    List<UserIpAddress> findByUserOrderByTimestampDesc(User user);
    List<UserIpAddress> findByUserAndIpAddress(User user, String ipAddress);
} 