package com.sda.spring.thymeleaf.service;

import com.sda.spring.thymeleaf.dto.ProductUpdate;
import com.sda.spring.thymeleaf.model.Product;
import com.sda.spring.thymeleaf.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // create
    public void save(Product product) {
        log.info("saving product");
        productRepository.save(product);
    }

    // find all
    public List<Product> findAll() {
        log.info("finding all products");
        return productRepository.findAll();
    }

    // find by id
    public Product findById(Long id) {
        log.info("finding by id");
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    // update
    public void update(Long productId, ProductUpdate productData) {
        log.info("update product {}", productData);

        productRepository.findById(productId)
                .map(existingProduct -> updateEntity(productData, existingProduct))
                .map(updatedProduct -> productRepository.save(updatedProduct))
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    private Product updateEntity(ProductUpdate productData, Product existingProduct) {
        existingProduct.setName(productData.getName());
        existingProduct.setPrice(productData.getPrice());
        return existingProduct;
    }

    public void updateNew(Product product) {
        log.info("update product {}", product);

        String name = product.getName();
        productRepository.findByNameIgnoreCase(name)
                .filter(existingProduct -> existingProduct.getId().equals(product.getId()))
                .map(existingProduct -> productRepository.save(product))
                .orElseThrow(() -> {
                    log.error("product with name {} already exists", name);
                    throw new ResourceAlreadyExistsException("product with name " + name + " already exists");
                });
    }

    // delete
    @Transactional
    public void delete(Long id) {
        log.info("deleting by id");
        productRepository.deleteById(id);
    }
}
