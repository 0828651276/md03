package org.codegym.baithithuchanhm3.enties;

public class Product {
    private int id;
    private String name;
    private String description;
    private int inventory;
    private int price;
    private String imageUrl;


    public Product(int id, String name, String description, int inventory, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(String name, String description, int inventory, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.imageUrl = imageUrl;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

