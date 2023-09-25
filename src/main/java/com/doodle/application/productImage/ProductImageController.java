package com.doodle.application.productImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/products/{productId}/images")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public ResponseEntity getAllProductImages(@PathVariable int productId) {
        List<ProductImage> productImages = productImageService.getAllProductImages(productId);

        List<ProductImageResponse> response = productImages.stream()
                .map(productImage -> new ProductImageResponse(productImage))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductImage(
            @PathVariable int productId,
            @PathVariable int id
    ) {
        ProductImage productImage = productImageService.getProductImage(
                id,
                productId
        );

        return ResponseEntity.ok(new ProductImageResponse(productImage));
    }

    @PostMapping
    public ResponseEntity createProductImage(
            @RequestPart("image") MultipartFile file,
            @PathVariable int productId
    ) throws IOException {
        ProductImage productImage = productImageService.storeProductImage(file, productId);

        return ResponseEntity.ok(new ProductImageResponse(productImage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductImage(
            @PathVariable int productId,
            @PathVariable int id
    ) throws IOException {
        ProductImage productImage = productImageService.getProductImage(id, productId);
        productImageService.deleteFromDatabase(productId, id);
        productImageService.deleteFromFileSystem(productImage.getFilePath());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
