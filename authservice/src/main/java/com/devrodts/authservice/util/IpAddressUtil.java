package com.devrodts.authservice.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class IpAddressUtil {
    
    /**
     * Extracts the client IP address from an HTTP request.
     * This method checks various headers that might contain the client IP address,
     * especially when the application is behind a proxy or load balancer.
     *
     * @param request The HTTP request
     * @return The client IP address
     */
    public String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (isInvalidIpAddress(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (isInvalidIpAddress(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isInvalidIpAddress(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isInvalidIpAddress(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (isInvalidIpAddress(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        
        // If multiple IPs are in the X-Forwarded-For header, take the first one
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        
        return ipAddress;
    }
    
    private boolean isInvalidIpAddress(String ipAddress) {
        return ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress);
    }
} 