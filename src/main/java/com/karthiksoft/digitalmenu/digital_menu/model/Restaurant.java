package com.karthiksoft.digitalmenu.digital_menu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;
    private String menuId;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMenuId() {
        return menuId;
    }

    // Getters and Setters
    public Restaurant() {}

    public Restaurant(String id, String name, String address, String menuId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and Setters
}
