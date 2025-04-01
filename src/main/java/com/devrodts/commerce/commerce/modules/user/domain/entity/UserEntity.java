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
}
