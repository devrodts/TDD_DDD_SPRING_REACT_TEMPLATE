package com.devrodts.authservice.controller;

import com.devrodts.authservice.entity.User;
import com.devrodts.authservice.entity.UserIpAddress;
import com.devrodts.authservice.service.IpAddressService;
import com.devrodts.authservice.util.IpAddressUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ip")
public class IpAddressController {

    private final IpAddressService ipAddressService;
    private final IpAddressUtil ipAddressUtil;

    @Autowired
    public IpAddressController(IpAddressService ipAddressService, IpAddressUtil ipAddressUtil) {
        this.ipAddressService = ipAddressService;
        this.ipAddressUtil = ipAddressUtil;
    }

    /**
     * Records the current user's IP address
     */
    @GetMapping("/record")
    public ResponseEntity<Map<String, Object>> recordCurrentIpAddress(
            @AuthenticationPrincipal User user,
            HttpServletRequest request) {
        
        String ipAddress = ipAddressUtil.getClientIpAddress(request);
        UserIpAddress userIpAddress = ipAddressService.recordUserIpAddress(user, ipAddress);
        
        return ResponseEntity.ok(Map.of(
            "message", "IP address recorded successfully",
            "ipAddress", ipAddress,
            "timestamp", userIpAddress.timestamp()
        ));
    }

    /**
     * Gets the IP address history for the current user
     */
    @GetMapping("/history")
    public ResponseEntity<List<UserIpAddress>> getUserIpHistory(@AuthenticationPrincipal User user) {
        List<UserIpAddress> ipHistory = ipAddressService.getUserIpHistory(user);
        return ResponseEntity.ok(ipHistory);
    }
} 