package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDto productDto);

    List<Product> findProduct(ProductDto productDto);

    Product findProductById(Long id);

    Product updateProduct(Long id, ProductDto productDto);

    String deleteProduct(Long id);
}
