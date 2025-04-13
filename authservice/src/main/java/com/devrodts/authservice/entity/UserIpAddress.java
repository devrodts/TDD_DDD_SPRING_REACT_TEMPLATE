package com.devrodts.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_ip_addresses")
public record UserIpAddress(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        UUID id,
        
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        User user,
        
        @Column(nullable = false)
        String ipAddress,
        
        @Column(nullable = false)
        LocalDateTime timestamp
) {
    public UserIpAddress() {
        this(null, null, null, null);
    }
    
    public static UserIpAddress createNew(User user, String ipAddress) {
        return new UserIpAddress(null, user, ipAddress, LocalDateTime.now());
    }
    
    @Override
    public String toString() {
        return "UserIpAddress[id=" + id + ", userId=" + (user != null ? user.id() : null) + 
               ", ipAddress=" + ipAddress + ", timestamp=" + timestamp + "]";
    }
} 