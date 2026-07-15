package com.by.sasa.bistrovic.web.shop;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repo;
    private final UserRepository userRepo;

    public AddressService(AddressRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public List<Address> getAll() {
        return repo.findAll();
    }

    public Address save(String userId, Address address) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // poveži adresu s userom
        address.setUser(user);

        // spremi adresu
        Address saved = repo.save(address);

        // dodaj adresu u user listu
        user.getSavedAddresses().add(saved);

        return saved;
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public List<Address> getUserAddresses(String userId) {
        return repo.findByUserId(userId);
    }
}