package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    public Optional<Order> findByReturnBarcode(String barcode);
    List<Order> findByUserId(String userId);

}
