package com.by.sasa.bistrovic.web.shop;

import java.util.List;

public class ReturnResponse {

    private Order order;
    private List<Product> items;

    public ReturnResponse(Product product) {
    }

    public ReturnResponse(Order order, List<Product> items) {
        this.order = order;
        this.items = items;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}
