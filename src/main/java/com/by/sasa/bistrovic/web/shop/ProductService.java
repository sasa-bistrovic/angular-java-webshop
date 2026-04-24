package com.by.sasa.bistrovic.web.shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() { return repo.findAll(); }
    public Optional<Product> getById(String id) { return repo.findById(id); }
    
    public Product save(Product product) {
        // Provjeri postoji li već proizvod
        Product existing = repo.findById(product.getId()).orElse(null);
        if (existing != null) {
            product.setReviews(existing.getReviews());
        }

        // Uklanjanje duplikata iz varijanti, opcija i sekcija
        if (product.getVariants() != null) {
            product.setVariants(
                product.getVariants()
                       .stream()
                       .distinct() // uklanja duplikate temeljem equals/hashCode
                       .toList()
            );
        }

        if (product.getPurchaseOptions() != null) {
            product.setPurchaseOptions(
                product.getPurchaseOptions()
                       .stream()
                       .distinct()
                       .toList()
            );
        }

        if (product.getSections() != null) {
            product.setSections(
                product.getSections()
                       .stream()
                       .distinct()
                       .toList()
            );
        }
        
        for (ProductVariant variant : product.getVariants()) {
            variant.setProductCategory(product.getCategory());
            variant.setProductImageUrl(product.getImageUrl());
            variant.setProductDescription(product.getDescription());
            variant.setProductName(product.getName());
            variant.setProductRating(product.getRating());
            variant.setProductReview(product.getReview());
            variant.setProductId(product.getId());

            if (variant.getFilters() != null) {
                for (FilterOption filter : variant.getFilters()) {
                    filter.setProductVariant(variant);
                }
            }            
            
            if (variant.getKeys()!= null) {
                for (KeyOption key : variant.getKeys()) {
                    key.setProductVariant(variant);
                }
            }                        
            
            if (variant.getOptions()!= null) {
                for (ProductVariantOption option : variant.getOptions()) {
                    option.setProductVariant(variant);
                }
            }            
        }
        
        double avg = product.getReviews()
            .stream()
            .mapToInt(Review::getRating)
            .average()
            .orElse(0);

        product.setRating(avg);
        product.setReview(product.getReviews().size());
        
        for (ProductVariant variant : product.getVariants()) {
            variant.setProductRating(avg);
            variant.setProductReview(product.getReviews().size());
        }         

        return repo.save(product);
    }
    
    public Product addReview(String productId, Review review) {

        Product product = repo.findById(productId).orElseThrow();

        review.setProduct(product);
        review.setDate(LocalDate.now());

        if (product.getReviews() == null) {
            product.setReviews(new ArrayList<>());
        }

        // provjera postoji li već review od tog usera
        Optional<Review> existingReview = product.getReviews()
            .stream()
            .filter(r -> r.getUserId().equals(review.getUserId()))
            .findFirst();

        if (existingReview.isPresent()) {

            // UPDATE review
            Review r = existingReview.get();
            r.setRating(review.getRating());
            r.setComment(review.getComment());
            r.setDate(LocalDate.now());

        } else {

            // ADD review
            product.getReviews().add(review);

        }

        // recalculating rating
        double avg = product.getReviews()
            .stream()
            .mapToInt(Review::getRating)
            .average()
            .orElse(0);

        product.setRating(avg);
        product.setReview(product.getReviews().size());
        
        for (ProductVariant variant : product.getVariants()) {
            variant.setProductRating(avg);
            variant.setProductReview(product.getReviews().size());
        }     

        return repo.save(product);
    }    
    
    public void delete(String id) { repo.deleteById(id); }

}
