package com.by.sasa.bistrovic.web.shop;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable String userId) {
        return orderService.getByUserId(userId);
    }
    
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {

        Optional<Order> order = orderService.getOrderById(id);

        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order.get());
    }    

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order) {
        for (CartItem item : order.getItems()) {
            item.setStatus("Processing");
        }
        return orderService.placeOrder(order);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        return orderService.updateStatus(id, body.get("status"));
    }

    @DeleteMapping("/{id}")
    public void cancelOrDelete(@PathVariable String id) {
        orderService.cancelOrder(id);
    }
/*    
    @PostMapping("/{orderId}/return-barcode")
    public Map<String, String> generateReturnBarcode(
            @PathVariable String orderId,
            @RequestBody Map<String, List<String>> body) {
        List<String> itemIds = body.get("itemIds");
        String barcode = orderService.generateReturnBarcode(orderId, itemIds);
        return Map.of("barcode", barcode);
    }

    @GetMapping("/process-return/{barcode}")
    public ResponseEntity<ReturnResponse> processReturn(@PathVariable String barcode) {

        ReturnResponse response = orderService.processReturn(barcode);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }    
*/
}
