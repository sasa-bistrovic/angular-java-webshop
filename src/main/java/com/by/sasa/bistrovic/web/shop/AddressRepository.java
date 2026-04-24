package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

    public List<Address> findByUserId(String userId);
}
