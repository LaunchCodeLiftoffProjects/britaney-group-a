package org.launchcode.britaneygroupa.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Product extends AbstractEntity {

    @NotNull
    @Size(min = 2, max = 20, message = "Invalid entry. Must be between 2 and 20 characters.")
    private String productName;

    @NotNull
    @Size(min = 2, max = 20, message = "Invalid entry. Must be between 2 and 20 characters.")
    private String productManufacturer;

    @NotNull
    private Date dateOfPurchase;

    @NotNull
    private Date dateOfExpiry;

    @NotNull
    @Size(min = 10, max = 100, message = "Invalid entry. Must be between 10 and 100 characters.")
    private String description;

    public Product() {
    }

    public Product(String productName, String productManufacturer, Date dateOfPurchase, Date dateOfExpiry, String description) {
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.dateOfPurchase = dateOfPurchase;
        this.dateOfExpiry = dateOfExpiry;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
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


    @Override
    public String toString() {
        return productName;
    }
}