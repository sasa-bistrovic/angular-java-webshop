package com.by.sasa.bistrovic.web.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product_item")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;

    private String imageUrl;
    private String mainImageUrl;

    
    @ElementCollection
    @CollectionTable(name = "list_image_url_item", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "column_image_url")
    private List<String> imageUrls;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @Column(columnDefinition = "TEXT")
    private String htmlDescription;    
        
    private double rating;
    private int review;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"savedCards","savedAddresses"})
    private User user;        
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;    
    
    @ManyToOne
    @JoinColumn(name = "creditcard_id")
    private CreditCard payment;  
    
    private int reviewCount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOption> purchaseOptions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSection> sections;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }    

    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }
    
    public String getMainImageUrl() { return mainImageUrl; }
    public void setMainImageUrl(String mainImageUrl) { this.mainImageUrl = mainImageUrl; }    

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getReview() { return review; }
    public void setReview(int review) { this.review = review; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }    
    
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public CreditCard getPayment() { return payment; }
    public void setPayment(CreditCard payment) { this.payment = payment; }            
    
    public List<Review> getReviews() { return reviews; }

    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public String getHtmlDescription() { return htmlDescription; }

    public void setHtmlDescription(String htmlDescription) { this.htmlDescription = htmlDescription; }    
    
    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public List<ProductVariant> getVariants() { return variants; }
    public void setVariants(List<ProductVariant> variants) { this.variants = variants; }

    public List<PurchaseOption> getPurchaseOptions() { return purchaseOptions; }
    public void setPurchaseOptions(List<PurchaseOption> purchaseOptions) { this.purchaseOptions = purchaseOptions; }

    public List<ProductSection> getSections() { return sections; }
    public void setSections(List<ProductSection> sections) { this.sections = sections; }    
}
