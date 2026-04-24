package com.by.sasa.bistrovic.web.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-variants")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class ProductVariantController {

    private final ProductVariantService service;

    public ProductVariantController(ProductVariantService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductVariant> getAllVariants() {
        return service.getAllVariants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariant> getVariantById(@PathVariable String id) {
        return service.getVariantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public List<ProductVariant> getVariantsByProduct(@PathVariable String productId) {
        return service.getVariantsByProductId(productId);
    }

    @PostMapping
    public ProductVariant createVariant(@RequestBody ProductVariant variant) {
        return service.createVariant(variant);
    }

    @PutMapping("/{id}")
    public ProductVariant updateVariant(@PathVariable String id, @RequestBody ProductVariant variantDetails) {
        return service.updateVariant(id, variantDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable String id) {
        service.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }
}