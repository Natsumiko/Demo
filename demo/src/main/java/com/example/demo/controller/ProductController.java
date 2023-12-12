package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(tags = "Product API", description = "APIs for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation("Get a list of all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get details for a specific product by ID")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ApiOperation("Create a new product")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update details of a specific product by ID")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @PutMapping("/{id}")
    @ApiOperation("Replace details of a specific product by ID")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        // For simplicity, treating PUT the same as PATCH
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove a specific product by ID")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
