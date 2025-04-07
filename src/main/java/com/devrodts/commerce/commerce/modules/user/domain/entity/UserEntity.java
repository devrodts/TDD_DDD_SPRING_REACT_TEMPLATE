package com.devrodts.commerce.commerce.modules.user.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String hashedPassword;
    private Timestamp userCreationTime;
    private Timestamp userUpdateTime;
    private String zipcode;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String phoneNumber;
    private String userRole;


    public UserEntity() {
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setUserCreationTime(Timestamp userCreationTime) {
        this.userCreationTime = userCreationTime;
    }

    public void setUserUpdateTime(Timestamp userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void streetAddress(String streetAddress){
        this.streetAddress = streetAddress;
    }

    public void city(String city){
        this.city = city;
    }

    public void state(String state){
        this.state = state;
    }

    public void country(String country){
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.userRole = role;
    }

}
