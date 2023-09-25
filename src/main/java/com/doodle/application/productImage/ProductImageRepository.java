package com.doodle.application.productImage;

import com.doodle.application.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findAllByProduct(Product product);

    Optional<ProductImage> findByName(String name);

    Optional<ProductImage> findByIdAndProduct(int id, Product product);
}
