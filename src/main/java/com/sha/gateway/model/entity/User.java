package com.sha.gateway.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Table(name = "USERS")
@javax.persistence.Entity
public class User
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @Id
    public Integer userID;

    @Column(nullable = false,unique = true,length = 150)
    private String username;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date created;
}
