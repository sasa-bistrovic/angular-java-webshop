package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findByOrderIsNull();
    
    public Optional<CartItem> findByReturnBarcode(String barcode);
    
    List<CartItem> findByCartUUIDAndOrderIsNull(String cartUUID);

    @Query("SELECT c.order FROM CartItem c WHERE c.id = :cartItemId")
    Order getOrderByCartId(@Param("cartItemId") String cartItemId);
}
