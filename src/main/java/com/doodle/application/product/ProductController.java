package com.doodle.application.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity getAllProducts() {
        List<Product> allProducts = productService.getAllProduct();

        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @PostMapping()
    public ResponseEntity createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product createdProduct = productService.createProduct(productDTO);

        return new ResponseEntity(new ProductResponse(createdProduct), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(
            @PathVariable int id,
            @RequestBody ProductDTO productDTO
    ) {
        Product updatedProduct = productService.updateProduct(id, productDTO);

        return ResponseEntity.ok(new ProductResponse(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
