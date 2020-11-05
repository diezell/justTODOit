package com.application.todoit.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_table")
@Data
public class UserEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

}
