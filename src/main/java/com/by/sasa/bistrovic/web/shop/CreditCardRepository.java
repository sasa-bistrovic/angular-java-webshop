package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

    public List<CreditCard> findByUserId(String userId);
}
