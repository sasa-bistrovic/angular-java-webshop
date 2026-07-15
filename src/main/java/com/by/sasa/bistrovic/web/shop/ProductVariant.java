package com.by.sasa.bistrovic.web.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_variant_item")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String imageUrl;
    private String productImageUrl;    
    private String productName;    
    private String productDescription;    
    private String productCategory;    
    private double productRating;    
    private int productReview;    
    private String color;
    private String description;
    private String category;
    private String size;
    private double price;
    private int stock;
    
    private String productId;
    
    @ElementCollection
    @CollectionTable(name = "list_variant_image_url_item", joinColumns = @JoinColumn(name = "product_variant_id"))
    @Column(name = "column_variant_image_url")
    private List<String> imageUrls;  
    
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantOption> options;  
    
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilterOption> filters;
    
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KeyOption> keys;    

    public ProductVariant() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getProductImageUrl() { return productImageUrl; }
    public void setProductImageUrl(String productImageUrl) { this.productImageUrl = productImageUrl; }    
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }        
    
    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }            
    
    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }            
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }                
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }                    
    
    public double getProductRating() { return productRating; }
    public void setProductRating(double productRating) { this.productRating = productRating; }                
    
    public int getProductReview() { return productReview; }
    public void setProductReview(int productReview) { this.productReview = productReview; }                    
    
    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }                        

    public List<FilterOption> getFilters() { return filters; }
    public void setFilters(List<FilterOption> filters) { this.filters = filters; }                            
    
    public List<KeyOption> getKeys() { return keys; }
    public void setKeys(List<KeyOption> keys) { this.keys = keys; }                                
    
    public List<ProductVariantOption> getOptions() { return options; }
    public void setOptions(List<ProductVariantOption> options) { this.options = options; }                                    
}

