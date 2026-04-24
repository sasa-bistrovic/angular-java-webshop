package com.by.sasa.bistrovic.web.shop;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class CreditCardController {

    private final CreditCardService service;

    public CreditCardController(CreditCardService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public List<CreditCard> getUserCards(@PathVariable String userId) {
        return service.getUserCards(userId);
    }

    @PostMapping("/user/add/{userId}")
    public CreditCard addCard(@PathVariable String userId, @RequestBody CreditCard card) {
        return service.save(userId, card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}