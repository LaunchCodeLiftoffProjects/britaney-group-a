package org.launchcode.srilc101.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Product extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String manufacturer;

    @NotNull
    private Date dateOfPurchase;

    @NotNull
    private Date dateOfExpiry;

    @NotNull
    private String description;

    @NotNull
    private int userId;

    public Product() {
    }

    public Product(String name, String manufacturer, Date dateOfPurchase, Date dateOfExpiry, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.dateOfPurchase = dateOfPurchase;
        this.dateOfExpiry = dateOfExpiry;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return name;
    }
}