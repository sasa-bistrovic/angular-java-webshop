package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final CreditCardRepository repo;
    private final UserRepository userRepo;

    public CreditCardService(CreditCardRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public List<CreditCard> getAll() {
        return repo.findAll();
    }
    
    public CreditCard save(String userId, CreditCard creditcard) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // poveži adresu s userom
        creditcard.setUser(user);

        // spremi adresu
        CreditCard saved = repo.save(creditcard);

        // dodaj adresu u user listu
        user.getSavedCards().add(creditcard);

        return saved;
    }    

    public void delete(String id) {
        repo.deleteById(id);
    }

    public List<CreditCard> getUserCards(String userId) {
        return repo.findByUserId(userId);
    }
}
