package com.devrodts.authservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devrodts.authservice.entity.User;
import com.devrodts.authservice.entity.UserIpAddress;
import com.devrodts.authservice.repository.UserIpAddressRepository;
import com.devrodts.authservice.service.IpAddressService;

@Service
public class IpAddressServiceImpl implements IpAddressService {

    private final UserIpAddressRepository userIpAddressRepository;

    @Autowired
    public IpAddressServiceImpl(UserIpAddressRepository userIpAddressRepository) {
        this.userIpAddressRepository = userIpAddressRepository;
    }

    @Override
    @Transactional
    public UserIpAddress recordUserIpAddress(User user, String ipAddress) {
        UserIpAddress userIpAddress = UserIpAddress.createNew(user, ipAddress);
        return userIpAddressRepository.save(userIpAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserIpAddress> getUserIpHistory(User user) {
        return userIpAddressRepository.findByUserOrderByTimestampDesc(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserIpAddress> getUserIpAddresses(User user, String ipAddress) {
        return userIpAddressRepository.findByUserAndIpAddress(user, ipAddress);
    }
} 