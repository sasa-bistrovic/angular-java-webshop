package com.by.sasa.bistrovic.web.shop;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class CartItemController {

    private final CartItemService cartService;
    private final CartItemRepository cartRepository;
    private final CreditCardRepository cardRepository;

    public CartItemController(CartItemService cartService, CartItemRepository cartRepository, CreditCardRepository cardRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/{cartUUID}")
    public List<CartItem> getAllItems(@PathVariable String cartUUID) {
        return cartService.getAllItems(cartUUID);
    }

    @GetMapping("/item/{id}")
    public CartItem getItem(@PathVariable String id) {
        return cartService.getItem(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request.getProductId(), request.getQuantity(), request.getCartUUID(), request.getProductName(), request.getProductImageUrl(), request.getProductPrice(), request.getProductSize(), request.getProductColor(), request.getVariantId());
    }

    @PutMapping("/update/{id}")
    public CartItem updateQuantity(@PathVariable String id,
                                   @RequestParam int quantity) {
        return cartService.updateQuantity(id, quantity);
    }
    
    @PutMapping("/{id}/status")
    public CartItem updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        return cartService.updateStatus(id, body.get("status"));
    }    

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable String id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-with-stock/{id}")
    public ResponseEntity<Void> removeFromCartWithSotck(@PathVariable String id) {
        cartService.removeFromCartWithStock(id);
        return ResponseEntity.ok().build();
    }    
/*
    @PostMapping("/{cartId}/return-barcode")
    public Map<String, String> generateReturnBarcode(
            @PathVariable String cartId,
            @RequestBody Map<String, List<String>> body) {
        List<String> itemIds = body.get("itemIds");
        String barcode = cartService.generateReturnBarcode(cartId, itemIds);
        return Map.of("barcode", barcode);
    }
*/
    @PostMapping("/{cartItemId}/return-barcode")
    public Map<String,String> generateReturnBarcode(
            @PathVariable String cartItemId,
            @RequestBody ReturnRequest request
    ) {

        CartItem cartItem = cartRepository.findById(cartItemId)
                .orElseThrow();

        CreditCard card = cardRepository
                .findById(request.getCreditCardId())
                .orElseThrow();

        String barcode = "RET-" + UUID.randomUUID().toString().substring(0,10);

        cartItem.setReturnBarcode(barcode);
        cartItem.setReturnCreditCard(card);
        cartItem.setReturnReason(request.getReturnReason());
        cartItem.setStatus("Return In Progress");

        cartRepository.save(cartItem);

        return Map.of("barcode", barcode);
    }    
    
    @GetMapping("/process-return/{barcode}")
    public ResponseEntity<CartReturnResponse> processReturn(@PathVariable String barcode) {

        CartReturnResponse response = cartService.processReturn(barcode);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }    
}
