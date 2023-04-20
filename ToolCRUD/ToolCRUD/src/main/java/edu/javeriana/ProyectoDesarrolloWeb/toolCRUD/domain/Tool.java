package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.domain;

import jakarta.persistence.*;

@Entity
public class Tool {
    @Id
    private Integer id;

    private String name, description, imageURL;
    @ManyToOne()
    @JoinColumn(name="brandid")
    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
