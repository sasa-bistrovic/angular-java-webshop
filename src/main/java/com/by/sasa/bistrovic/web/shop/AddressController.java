package com.by.sasa.bistrovic.web.shop;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<Address> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<Address> getUserAddresses(@PathVariable String userId) {
        return service.getUserAddresses(userId);
    }

    @PostMapping("/user/add/{userId}")
    public Address addAddress(@PathVariable String userId, @RequestBody Address address) {
        return service.save(userId, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
