package com.example.q2_test;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product newProduct) {
        Product savedProduct = productRepository.save(newProduct);
        URI location = URI.create(String.format("/api/products/%d", savedProduct.getId()));

        return ResponseEntity.created(location).body(new ProductResponse("Product created successfully", savedProduct));
    }

    static class ProductResponse {
        private String message;
        private Product product;

        public ProductResponse(String message, Product product) {
            this.message = message;
            this.product = product;
        }

        public String getMessage() {
            return message;
        }

        public Product getProduct() {
            return product;
        }
    }
}
