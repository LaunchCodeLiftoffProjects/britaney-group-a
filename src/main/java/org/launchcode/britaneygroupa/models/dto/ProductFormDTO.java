package org.launchcode.britaneygroupa.models.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ProductFormDTO {

    @NotNull
    @Size(min = 2, max = 20, message = "Invalid entry. Must be between 2 and 20 characters.")
    private String name;

    @NotNull
    @Size(min = 2, max = 20, message = "Invalid entry. Must be between 2 and 20 characters.")
    private String manufacturer;

    @NotNull
    private Date dateOfPurchase;

    @NotNull
    private Date dateOfExpiry;

    @NotNull
    @Size(min = 5, max = 50, message = "Invalid entry. Must be between 10 and 100 characters.")
    private String description;

    @NotNull
    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
