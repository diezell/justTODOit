package com.application.todoit.Models;

import lombok.Data;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.*;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

}
