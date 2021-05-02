package com.ait.store.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "products")
public class Product {

    public enum Type {
        Sweets, Cereal, Meat, Vegetables, Fruit, Drink, Alcohol, Dairy, Frozen
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private long productId;

    @Column
    @Size(min=2, message="Name should have at least 2 characters")
    private String name;

    @Column
    private String company;

    @Column
    private double price;

    @Column(name="expiry_date", columnDefinition = "DATE")
    private LocalDate expiryDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    @Size(max=100, message="description should have no more than 100 characters")
    private String description;

    @Column(name="country_made")
    private String countryMade;

    private long productValue;

    @JsonIgnore
    @ManyToMany(mappedBy = "shopProducts")
    private List<Shop> productShops = new ArrayList<>();

    public Product(String name, String countryMade) {
    }

    public void productShops(Shop store){
        productShops.add(store);
    }

    public Product() {
    }

    public Product(long productId, String name, String company, double price, LocalDate expiryDate, Type type, String description, String countryMade, long productValue) {
        this.productId = productId;
        this.name = name;
        this.company = company;
        this.price = price;
        this.expiryDate = expiryDate;
        this.type = type;
        this.description = description;
        this.countryMade = countryMade;
        this.productValue = productValue;
    }

    public long getProductValue() {
        return productValue;
    }

    public void setProductValue(long productValue) {
        this.productValue = productValue;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryMade() {
        return countryMade;
    }

    public void setCountryMade(String countryMade) {
        this.countryMade = countryMade;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", expiryDate=" + expiryDate +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", countryMade='" + countryMade + '\'' +
                ", productShops=" + productShops +
                '}';
    }
}


