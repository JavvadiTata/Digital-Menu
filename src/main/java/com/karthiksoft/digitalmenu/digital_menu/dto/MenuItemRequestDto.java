package com.karthiksoft.digitalmenu.digital_menu.dto;

import org.springframework.web.multipart.MultipartFile;

public class MenuItemRequestDto {
    private String category;
    private String itemName;
    private double price;
    private MultipartFile itemImage; // Use this to upload the image

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public MultipartFile getItemImage() {
        return itemImage;
    }

    public void setItemImage(MultipartFile itemImage) {
        this.itemImage = itemImage;
    }


    // Getters and Setters
}


