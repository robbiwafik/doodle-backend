package com.doodle.application.product;

import com.doodle.application.category.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find a product with id " + id + "."));
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .numberInStock(productDTO.getNumberInStock())
                .category(categoryService.getCategory(productDTO.getCategoryId()))
                .build();

        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductDTO productDTO) {
        Product product = getProduct(id);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setNumberInStock(productDTO.getNumberInStock());
        product.setCategory(
                categoryService.getCategory(productDTO.getCategoryId())
        );

        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }
}
