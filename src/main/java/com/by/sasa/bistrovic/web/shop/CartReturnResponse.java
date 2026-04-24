package com.by.sasa.bistrovic.web.shop;

import java.util.List;

public class CartReturnResponse {

    private Order order;
    private CartItem item;
    private CreditCard returnCreditCard;
    private CreditCard productCreditCard;
    private String returnReason;
    private int quantity;

    public CartReturnResponse(Order order, CartItem item, CreditCard returnCreditCard, CreditCard productCreditCard, String returnReason, int quantity) {
        this.order = order;
        this.item = item;
        this.returnCreditCard = returnCreditCard;
        this.productCreditCard = productCreditCard;
        this.returnReason = returnReason;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public CartItem getItem() {
        return item;
    }
    
    public CreditCard getReturnCreditCard() {
        return returnCreditCard;
    }    

    public CreditCard getProductCreditCard() {
        return productCreditCard;
    }        
    
    public String getReturnReason() {
        return returnReason;
    }        
    
    public int getQuantity() {
        return quantity;
    }       
}

