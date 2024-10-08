package com.vikram.macquarie.bankdemo.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name="user_id", nullable = false)
    private String userId;

    @Column(name="user_name", nullable = false)
    private String userName;

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore //needed to avoid infinite recursion
    List<AccountEntity> accountEntities;

    public UserEntity(String userId) {
        this.userId = userId;
    }
}
