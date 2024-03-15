package com.ecommerce.controller;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity addProduct(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<List<ProductDto>> findProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productMapper.modelstoDtos(productService.findProduct(productDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.modelToDto(productService.findProductById(id)));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productMapper.modelToDto(productService.updateProduct(product.getId(), productMapper.modelToDto(product))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
