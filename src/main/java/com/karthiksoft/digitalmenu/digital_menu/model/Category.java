package com.karthiksoft.digitalmenu.digital_menu.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<MenuItem> items = new ArrayList<>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
