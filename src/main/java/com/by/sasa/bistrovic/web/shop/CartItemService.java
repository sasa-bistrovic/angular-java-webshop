package com.by.sasa.bistrovic.web.shop;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository repo;
    private final ProductService productService;
    private final ProductVariantService productVariantService;
    private final OrderRepository orderRepository;

    public CartItemService(CartItemRepository repo, ProductService productService, ProductVariantService productVariantService, OrderRepository orderRepository) {
        this.repo = repo;
        this.productService = productService;
        this.productVariantService = productVariantService;
        this.orderRepository = orderRepository;
    }

    public List<CartItem> getAllItems(String cartUUID) {
        return repo.findByCartUUIDAndOrderIsNull(cartUUID);
    }

    public Optional<CartItem> getItem(String id) {
        return repo.findById(id);
    }
    
    public CartItem updateStatus(String cartId, String status) {
        return repo.findById(cartId).map(cartItem -> {
            if (!cartItem.getStatus().equals("Return In Progress") && !cartItem.getStatus().equals("Returned")) {
            cartItem.setStatus(status);
            }
            return repo.save(cartItem);
        }).orElse(null);
    }    

    public CartItem addToCart(String productId, int quantity, String cartUUID, String productName, String productImageUrl, double productPrice, String productSize, String productColor, String variantId) {

        Product product = productService.getById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setCartUUID(cartUUID);
        item.setProductName(productName);
        item.setProductImageUrl(productImageUrl);
        item.setProductPrice(productPrice);
        item.setProductSize(productSize);
        item.setProductColor(productColor);
        item.setVariantId(variantId);

        return repo.save(item);
    }

    public CartItem updateQuantity(String id, int quantity) {

        CartItem item = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        item.setQuantity(quantity);

        return repo.save(item);
    }

    @Transactional
    public void removeFromCart(String id) {

        CartItem item = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        item.setProduct(null);

        repo.delete(item);
    }
    
    @Transactional
    public void removeFromCartWithStock(String id) {

        CartItem item = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        ProductVariant variant = productVariantService
            .getVariantById(item.getVariantId())
            .orElseThrow();

        variant.setStock(variant.getStock() + item.getQuantity());

        productVariantService.createVariant(variant);

        item.setProduct(null);

        repo.delete(item);
    }    

    @Transactional
    public void clearCart() {
        
        List<CartItem> items = repo.findAll();

        for (CartItem item : items) {
            
                        // Ako CartItem nije u nekoj narudžbi, možemo ga obrisati
            if (item.getOrder() == null) {
                item.setProduct(null);
                item.setOrder(null);
                repo.delete(item);
            } else {
                // inače samo razveži iz korpe, ali ostavi u bazi za Order
                item.setProduct(null);  // ili neki drugi marker da više nije u korpi
                item.setOrder(null);
                repo.delete(item);
            }        
        }
    }    
    
    public String generateReturnBarcode(String cartId, List<String> itemIds) {

        CartItem cart = repo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        String rawBarcode = "RET" + cartId; // start with "RET" + orderId
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

        cart.setReturnBarcode(barcode);
        cart.setStatus("Return In Progress");

        repo.save(cart);

        return barcode;
    }

@Transactional
public CartReturnResponse processReturn(String barcode) {

    barcode = barcode.trim();

    Optional<CartItem> optionalCartItem = repo.findByReturnBarcode(barcode);

    if (optionalCartItem.isEmpty()) {
        return null;
    }

    CartItem cartItem = optionalCartItem.get();
    
    if (!cartItem.getStatus().equals("Returned")) {
        
    ProductVariant variant = productVariantService
            .getVariantById(cartItem.getVariantId())
            .orElseThrow();

        variant.setStock(variant.getStock() + cartItem.getQuantity());

        productVariantService.createVariant(variant);    
    
    cartItem.setStatus("Returned");

    repo.save(cartItem);
    
    } else {
        cartItem.setOrder(null);
        cartItem.setProduct(null);
        cartItem.setReturnReason(null);
        cartItem.setReturnCreditCard(null);
        cartItem.setQuantity(0);
        cartItem = null;
    }

    return new CartReturnResponse(
            cartItem.getOrder(),
            cartItem,
            cartItem.getReturnCreditCard(),
            cartItem.getProduct().getPayment(),            
            cartItem.getReturnReason(),
            cartItem.getQuantity()
    );
}  
    
public String generateReturnBarcodeForItem(String cartItemId) {

    CartItem item = repo.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

    // Generiraj jedinstveni barcode za item
    String barcode = "RET" + item.getId();

    item.setReturnBarcode(barcode);
    
    item.setStatus("Returned");
    
    // Postavi status ordera na RETURNED ako nije već
    Order order = item.getOrder();
    if (!"RETURNED".equals(order.getStatus())) {
        order.setStatus("RETURNED");
    }

    repo.save(item);

    return barcode;
}    
}