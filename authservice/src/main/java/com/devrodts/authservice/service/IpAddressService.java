package com.devrodts.authservice.service;

import com.devrodts.authservice.entity.User;
import com.devrodts.authservice.entity.UserIpAddress;
import java.util.List;

public interface IpAddressService {
    /**
     * Records a user's IP address
     * 
     * @param user The user
     * @param ipAddress The IP address to record
     * @return The created UserIpAddress entity
     */
    UserIpAddress recordUserIpAddress(User user, String ipAddress);
    
    /**
     * Gets the IP address history for a user
     * 
     * @param user The user
     * @return List of IP addresses ordered by timestamp (newest first)
     */
    List<UserIpAddress> getUserIpHistory(User user);
    
    /**
     * Checks if a user has accessed from a specific IP address
     * 
     * @param user The user
     * @param ipAddress The IP address to check
     * @return List of records for this IP address
     */
    List<UserIpAddress> getUserIpAddresses(User user, String ipAddress);
} 