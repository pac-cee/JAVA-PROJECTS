package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ProductSearchRepository;
import com.example.ecommerce.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductSearchRepository searchRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        String cacheKey = "product:" + id;
        Product product = (Product) redisTemplate.opsForValue().get(cacheKey);
        
        if (product == null) {
            product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
            redisTemplate.opsForValue().set(cacheKey, product, 1, TimeUnit.HOURS);
        }
        
        return product;
    }

    @Transactional
    public Product create(Product product) {
        Product savedProduct = productRepository.save(product);
        searchRepository.save(savedProduct);
        return savedProduct;
    }

    @Transactional
    public Product update(Long id, Product productDetails) {
        Product product = findById(id);
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategory(productDetails.getCategory());
        
        Product updatedProduct = productRepository.save(product);
        searchRepository.save(updatedProduct);
        redisTemplate.delete("product:" + id);
        
        return updatedProduct;
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        searchRepository.delete(product);
        redisTemplate.delete("product:" + id);
    }

    @Transactional(readOnly = true)
    public Page<Product> search(String query, Pageable pageable) {
        return searchRepository.findByNameContainingOrDescriptionContaining(query, query, pageable);
    }
} 