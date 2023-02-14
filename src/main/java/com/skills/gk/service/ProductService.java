package com.skills.gk.service;

import com.skills.gk.dto.ProductRequest;
import com.skills.gk.model.Product;
import com.skills.gk.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product with id {} is saved in mongo db", product.getId());
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
