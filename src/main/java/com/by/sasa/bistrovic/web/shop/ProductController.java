package com.by.sasa.bistrovic.web.shop;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getById(id).orElse(null);
    }

    @PostMapping("")
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }
    
    @PostMapping("/{id}/review")
    public Product addReview(@PathVariable String id, @RequestBody Review review) {
        return productService.addReview(id, review);
    }    

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) { productService.delete(id); }    
}