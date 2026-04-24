package com.by.sasa.bistrovic.web.shop;

import jakarta.transaction.Transactional;
import java.util.Date;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repo;
    private final ProductService productService;
    private final ProductVariantService productVariantService;

    public OrderService(OrderRepository repo, ProductService productService, ProductVariantService productVariantService) {
        this.repo = repo;
        this.productService = productService;
        this.productVariantService = productVariantService;
    }

    public List<Order> getByUserId(String userId) {
    List<Order> allOrders = repo.findAll();

    return allOrders.stream()
        .filter(order -> 
            userId.equals(order.getUserId()) ||
            order.getItems().stream()
                 .anyMatch(item -> item.getProduct() != null &&
                                   userId.equals(item.getProduct().getUser().getId()))
        )
        .toList();
    }
    
    public Optional<Order> getOrderById(String id) {
        return repo.findById(id);
    }    

    @Transactional
    public Order placeOrder(Order order) {

    order.getItems().forEach(item -> {

        ProductVariant variant = productVariantService
            .getVariantById(item.getVariantId())
            .orElseThrow();

        variant.setStock(variant.getStock() - item.getQuantity());

        productVariantService.createVariant(variant);        
        
        item.setOrder(order);
    });

        order.setOrderDate(new Date());
        order.setStatus("Processing");

        return repo.save(order);
    }

    @Transactional
    public void cancelOrder(String orderId) {
        Order order = repo.findById(orderId).orElseThrow();
        
        order.getItems().forEach(item -> {

        ProductVariant variant = productVariantService
            .getVariantById(item.getVariantId())
            .orElseThrow();

        variant.setStock(variant.getStock() + item.getQuantity());

        productVariantService.createVariant(variant);        
    });

        repo.delete(order);
    }

    public Order updateStatus(String orderId, String status) {
        return repo.findById(orderId).map(order -> {
            order.setStatus(status);
            return repo.save(order);
        }).orElse(null);
    }
/*    
    public String generateReturnBarcode(String orderId, List<String> itemIds) {

        Order order = repo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String rawBarcode = "RET" + orderId; // start with "RET" + orderId
        String joinedItems = String.join("", itemIds); // concatenate all itemIds without underscores

        // Combine and pad/truncate to 22 characters
        String barcode = (rawBarcode + joinedItems); // initial combined string

        // Ensure length = 22
        if (barcode.length() < 22) {
            // pad with zeros at the end
            barcode = String.format("%-22s", barcode).replace(' ', '0');
        } else if (barcode.length() > 22) {
            // truncate to 22 characters
            barcode = barcode.substring(0, 22);
        }

        order.setReturnBarcode(barcode);
        order.setReturnedItems(itemIds);

        order.setStatus("RETURNED");

        repo.save(order);

        return barcode;
    }

    public ReturnResponse processReturn(String barcode) {

        Optional<Order> optionalOrder = repo.findByReturnBarcode(barcode);

        if (optionalOrder.isEmpty()) {
            return null;
        }

        Order order = optionalOrder.get();

        List<Product> items = order.getItems().stream()
                .filter(i -> order.getReturnedItems().contains(i.getProduct().getId()))
                .map(CartItem::getProduct)
                .toList();

        return new ReturnResponse(order, items);
    }    
*/
}