package org.buysell.model;


public class Product {
    private long id;
    private String title;
    private String description;
    private float price;
    private String author;

    public Product(){

    }

    public Product(long id, String title, String description, float price, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.author = author;
    }

    //region Getters

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    //endregionss

    //region Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //endregion
}
