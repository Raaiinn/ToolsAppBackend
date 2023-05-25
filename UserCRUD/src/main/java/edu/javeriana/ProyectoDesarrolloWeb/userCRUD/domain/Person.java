package edu.javeriana.ProyectoDesarrolloWeb.userCRUD.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Person {
    @Id
    private Integer id;
    private String name, lastname;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference
    private List<User> users;

    @ManyToOne()
    @JoinColumn(name="cityid")
    @JsonManagedReference
    private City city;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

