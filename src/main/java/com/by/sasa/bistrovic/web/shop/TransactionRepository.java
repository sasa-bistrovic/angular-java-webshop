package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    public List<Transaction> findByUserId(String userId);
}

