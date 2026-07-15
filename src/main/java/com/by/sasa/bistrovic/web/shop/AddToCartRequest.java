package com.by.sasa.bistrovic.web.shop;

public class AddToCartRequest {
    private String productId;
    private int quantity;
    private String cartUUID;
    private String productName;
    private String productImageUrl;
    private double productPrice;
    private String productSize;
    private String productColor;
    private String variantId;

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getCartUUID() { return cartUUID; }
    public void setCartUUID(String cartUUID) { this.cartUUID = cartUUID; }
    
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
