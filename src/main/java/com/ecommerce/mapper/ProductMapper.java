package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto modelToDto(Product product);

    Product dtoToModel(ProductDto productDto);

    List<ProductDto> modelstoDtos(List<Product> products);
}
