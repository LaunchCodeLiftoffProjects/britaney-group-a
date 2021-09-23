package org.launchcode.britaneygroupa.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String manufacturer;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfPurchase;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfExpiry;

    @NotNull
    private String description;

    @NotNull
    private int userId;

    @OneToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(length = 64)

    private String fileName;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Transient

    private MultipartFile image;

    @Transient
    public String getPhotosImagePath() {
        if (fileName == null) return null;
        return "/Product-image/" + id + "/" + fileName;
    }

    public Product() {
    }

    public Product(Integer id, String name, String manufacturer, Date dateOfPurchase, Date dateOfExpiry, String description, String fileName) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.dateOfPurchase = dateOfPurchase;
        this.dateOfExpiry = dateOfExpiry;
        this.description = description;
        this.fileName = fileName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return name;
    }
}