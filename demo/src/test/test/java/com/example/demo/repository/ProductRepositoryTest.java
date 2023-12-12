package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveAndRetrieveProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setCode("P001");
        product.setName("Test Product");
        product.setDescription("Description");
        product.setPrice((int) 29.99);
        product.setQuantity(50);
        product.setInventoryStatus("In Stock");
        product.setCategory("Test Category");

        productRepository.save(product);

        Optional<Product> retrievedProduct = productRepository.findById(product.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Test Product", retrievedProduct.get().getName());
    }

    // Ajoutez d'autres tests CRUD au besoin
}

