package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand {
    @Id
    private Integer id;

    private String name;
    @OneToMany(mappedBy = "brand")
    private List<Tool> tools;

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
}
