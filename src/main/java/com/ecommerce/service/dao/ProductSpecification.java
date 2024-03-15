package com.ecommerce.service.dao;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public Specification<Product> buildProductSpecification(ProductDto productDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + productDto.getName() + "%"));
            predicates.add(criteriaBuilder.equal(root.get("price"), productDto.getPrice()));
            predicates.add(criteriaBuilder.equal(root.get("quantity"), productDto.getQuantity()));

            Predicate[] predicateArr = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(predicateArr));
        };
    }
}
