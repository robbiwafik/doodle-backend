package com.doodle.application.productImage;

import com.doodle.application.product.ProductService;
import com.doodle.application.utils.UniqueStringGenerator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductService productService;

    private final String uploadDir;

    public ProductImageService(
            @Value("${application.file.upload-dir}") String uploadDir
    ) {
        this.uploadDir = uploadDir + "productImages/";
    }

    public List<ProductImage> getAllProductImages(int productId) {
        return productImageRepository.findAllByProduct(
                productService.getProduct(productId)
        );
    }

    public ProductImage storeProductImage(MultipartFile image, int id) throws IOException {
        String uniqueString = UniqueStringGenerator.generateUniqueString();
        String fileName = uniqueString + "_" + image.getOriginalFilename();
        String storedImagePath = uploadDir + fileName;

        ProductImage productImage = ProductImage.builder()
                .name(fileName)
                .filePath(storedImagePath)
                .type(image.getContentType())
                .product(productService.getProduct(id))
                .build();
        image.transferTo(new File(storedImagePath));

        return productImageRepository.save(productImage);
    }

    public ProductImage getProductImage(int id, int productId) {
        return productImageRepository
                .findByIdAndProduct(
                        id,
                        productService.getProduct(productId)
                )
                .orElseThrow(
                        () -> new EntityNotFoundException("Couldn't find an image with given id " + id + " associated with product with id " + productId)
                );
    }

    public Map<String, Object> retrieveProductImage(String name) throws IOException {
        ProductImage productImageInstance = productImageRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Product image does not exist."));
        Path path = new File(productImageInstance.getFilePath()).toPath();
        byte[] productImageBytes = Files.readAllBytes(path);

        Map<String, Object> productImage = new HashMap<>();
        productImage.put("instance", productImageInstance);
        productImage.put("bytes", productImageBytes);

        return productImage;
    }

    public void deleteFromDatabase(
            int productId,
            int id
    ) {
        ProductImage productImage = getProductImage(id, productId);
        productImageRepository.delete(productImage);
    }

    public void deleteFromFileSystem(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.delete(path);
    }
}
