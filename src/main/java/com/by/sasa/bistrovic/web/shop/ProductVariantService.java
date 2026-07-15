package com.by.sasa.bistrovic.web.shop;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantService {

    private final ProductVariantRepository repository;

    public ProductVariantService(ProductVariantRepository repository) {
        this.repository = repository;
    }

    public List<ProductVariant> getAllVariants() {
        return repository.findAll();
    }

    public Optional<ProductVariant> getVariantById(String id) {
        return repository.findById(id);
    }

    public List<ProductVariant> getVariantsByProductId(String productId) {
        return repository.findByProductId(productId);
    }

    public ProductVariant createVariant(ProductVariant variant) {
        return repository.save(variant);
    }

    public ProductVariant updateVariant(String id, ProductVariant variantDetails) {
        return repository.findById(id)
                .map(variant -> {
                    variant.setName(variantDetails.getName());
                    variant.setImageUrl(variantDetails.getImageUrl());
                    variant.setColor(variantDetails.getColor());
                    variant.setSize(variantDetails.getSize());
                    variant.setPrice(variantDetails.getPrice());
                    variant.setStock(variantDetails.getStock());
                    variant.setProductId(variantDetails.getProductId());
                    return repository.save(variant);
                }).orElseThrow(() -> new RuntimeException("Variant not found with id " + id));
    }

    public void deleteVariant(String id) {
        repository.deleteById(id);
    }
}

