package com.by.sasa.bistrovic.web.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String cartUUID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("items")
    private Order order;   

    private int quantity;
    
    private String productName;
    private String productColor;
    private String productSize;
    private String productImageUrl;
    private double productPrice;
    
    private String trackingNumber;

    private String returnBarcode;      
    
    private String status;      
    
    private String variantId;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address returnAddress;    
    
    @ManyToOne
    @JoinColumn(name = "creditcard_id")
    private CreditCard returnCreditCard;               
    
    private String returnReason;      

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getReturnBarcode() {
        return returnBarcode;
    }

    public void setReturnBarcode(String returnBarcode) {
        this.returnBarcode = returnBarcode;
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }    
    
    public Address getReturnAddress() { return returnAddress; }
    public void setReturnAddress(Address returnAddress) { this.returnAddress = returnAddress; }

    public CreditCard getReturnCreditCard() { return returnCreditCard; }
    public void setReturnCreditCard(CreditCard returnCreditCard) { this.returnCreditCard = returnCreditCard; }        

    public String getReturnReason() { return returnReason; }
    public void setReturnReason(String returnReason) { this.returnReason = returnReason; }        
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }        
    
    public String getProductColor() { return productColor; }
    public void setProductColor(String productColor) { this.productColor = productColor; }            
    
    public String getProductSize() { return productSize; }
    public void setProductSize(String productSize) { this.productSize = productSize; }        

    public String getProductImageUrl() { return productImageUrl; }
    public void setProductImageUrl(String productImageUrl) { this.productImageUrl = productImageUrl; }            
    
    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }            
    
    public String getVariantId() { return variantId; }
    public void setVariantId(String variantId) { this.variantId = variantId; }                
}

