package com.application.todoit.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Listoflists {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

//    public Listoflists() {
//    }
//
//    public Listoflists(long id, String name, String description) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
