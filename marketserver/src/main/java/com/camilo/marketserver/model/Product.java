package com.camilo.marketserver.model;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private int id;
    private String name;
    private String description;

    public Product() {
    }


    public Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    @JsonCreator
    public Product(@JsonProperty("name") String name, @JsonProperty("description") String description) {
        Random r = new Random();
        this.id = r.nextInt(20000);
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product [description=" + description + ", id=" + id + ", name=" + name + "]";
    }

    public int getId() {
        return id;
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
