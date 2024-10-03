package com.karthiksoft.digitalmenu.digital_menu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "menus")
public class Menu {
    @Id
    private String id;
    private List<Category> categories;

    // Getters and Setters

    public Menu() {}

    public Menu(String id, List<Category> categories) {
        this.id = id;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addItemToCategory(String categoryName, MenuItem newItem) {
        for (Category category : this.categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                category.getItems().add(newItem);
                return;
            }
        }
        Category newCategory = new Category(categoryName);
        newCategory.getItems().add(newItem);
        this.categories.add(newCategory);
    }
}

