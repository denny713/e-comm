package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.dao.ProductSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            return productRepository.save(product);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<Product> findProduct(ProductDto productDto) {
        ProductSpecification specification = new ProductSpecification();
        return productRepository.findAll(specification.buildProductSpecification(productDto));
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }

        Product product = productOpt.get();
        BeanUtils.copyProperties(productDto, product);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }

        productRepository.delete(product.get());
        return "Success";
    }
}
