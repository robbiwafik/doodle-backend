package com.doodle.application.utils;

import com.doodle.application.productImage.ProductImage;
import com.doodle.application.productImage.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/uploads")
public class UploadController {
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/product/images/{name}")
    public ResponseEntity retrieveProductImage(
            @PathVariable String name
    ) throws IOException {
        Map<String, Object> productImage = productImageService.retrieveProductImage(name);
        ProductImage instance = (ProductImage) productImage.get("instance");
        byte[] bytes = (byte[]) productImage.get("bytes");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(instance.getType()))
                .body(bytes);
    }
}
