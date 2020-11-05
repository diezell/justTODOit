package com.application.todoit.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role_table")
@Data
public class RoleEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column
    private String name;

}
