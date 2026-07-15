package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByProductId(String productId);
}

