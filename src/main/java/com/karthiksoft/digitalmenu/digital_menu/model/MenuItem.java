package com.karthiksoft.digitalmenu.digital_menu.model;

public class MenuItem {
    private String itemName;
    private double price;
    private String imageUrl;  // Store the image URL here

    public MenuItem(String itemName, double price, String imageUrl) {
        this.itemName = itemName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
